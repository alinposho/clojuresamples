(ns bookexamples.forclojure.levenshtein-distance-dynamic
  (:import (java.lang Integer)))

;;; Problem 101:
;;  Given two sequences x and y, calculate the Levenshtein distance of x and y, i. e. the minimum number of edits needed to transform x into y. The allowed edits are:
;;   - insert a single item
;;   - delete a single item
;;   - replace a single item with another item
;;  Function memoization should make the simple algorithm work like a dynamic programming algorith(see the memoization example)

;; The goal is to write a dynamic programming solution to the levenshtein distance problem
; This solution is in the spirit of the dynamic programming in that it trades off space for computation time
; by using an atom to store intermediate states. The more elegat solution, using memoize, failed the 4clojure evaluation
(defn levenshtein [xs ys]
  (let [atm (atom {})
        get-or-update (fn [atm fct & args]
                        (if (@atm args)
                          (@atm args)
                          (let [newval (apply fct args)]
                               (swap! atm assoc args newval)
                               newval)))
        lev (fn lev [xs ys]
               (cond (zero? (count xs)) (count ys)
                     (zero? (count ys)) (count xs)
                     :else (min
                             (inc (get-or-update atm lev (rest xs) ys))
                             (inc (get-or-update atm lev xs (rest ys)))
                             (+ (get-or-update atm lev (rest xs) (rest ys))
                                (if (= (first xs) (first ys)) 0 1)))))]
    (lev xs ys)))


(comment

  (load-file "src/bookexamples/forclojure/levenshtein_distance.clj")
  (refer 'bookexamples.forclojure.levenshtein-distance)

  ;; Helper functions

  (defn get-or-update [atm fct & args]
    (if (@atm args)
      (@atm args)
      (let [newval (apply fct args)]
        (swap! atm assoc args newval)
        newval)))

  ;; Tests of the helper functions
  (def atm (atom {}))
  (get-or-update atm inc 0)
  atm


  (time (= (levenshtein "kitten" "sitting") 3))
  (time (= (levenshtein "closure" "clojure") (levenshtein "clojure" "closure") 1))
  (time (= (levenshtein "xyx" "xyyyx") 2))
  (time (= (levenshtein "" "123456") 6))
  (time (= (levenshtein "Clojure" "Clojure") (levenshtein "" "") (levenshtein [] []) 0))
  (time (= (levenshtein [1 2 3 4] [0 2 3 4 5]) 2))
  (time (= (levenshtein '(:a :b :c :d) '(:a :d)) 2))
  (time (= (levenshtein "ttttattttctg" "tcaaccctaccat") 10))
  (time (= (levenshtein "gaattctaatctc" "caaacaaaaaattt") 9))

  ;; Some of the solutions on the web
  ; This one I prefer because it was able to fix the problem that I had with my initial solution not working on the web
  ; evaluator
  (letfn [(dis [dis-memoized x1 x2]
               (cond
                 (= 0 (count x1)) (count x2)
                 (= 0 (count x2)) (count x1)
                 true (apply min (remove nil? [(inc (dis-memoized dis-memoized (rest x1) (rest x2)))
                                               (inc (dis-memoized dis-memoized x1 (rest x2)))
                                               (inc (dis-memoized dis-memoized (rest x1) x2))
                                               (when (= (first x1) (first x2)) (dis-memoized dis-memoized (rest x1) (rest x2)))]))))]
    (partial dis (memoize dis)))

  ; Some other non-interesting solutions
  (fn [x y]
    (letfn [
            (nextrow [[a b & more :as lastrow]
                      letter
                      [w & word]
                      acc]
                     (if b
                       (let [nextint (if (= letter w)
                                       a
                                       (+ 1 (min a b (first acc))))]
                         (recur (rest lastrow)
                                letter
                                word
                                (cons nextint acc)))
                       (reverse acc)))
            (leven ([source target] (leven source target 1
                                           (range (inc (count target)))))
                   ([[sletter & source] target n lastrow]
                    (if sletter
                      (let [thisrow (nextrow lastrow sletter target [n])]
                        (recur source target (inc n) thisrow))
                      (last lastrow))))
            ]
      (leven x y)))

  (fn [a b]
    (letfn [(step [calculated-result coordinate]
                  (let [x (first coordinate)
                        y (last coordinate)]
                    (assoc calculated-result
                           coordinate
                           (if (zero? (min x y))
                             (max x y)
                             (min
                               (inc (calculated-result [(dec x) y]))
                               (inc (calculated-result [x (dec y)]))
                               (+
                                 (calculated-result [(dec x) (dec y)])
                                 (if (= ((vec a) (dec x)) ((vec b) (dec y))) 0 1)))))))]
      ((reduce step {} (apply concat (map #(map (fn [i] [% i]) (range 0 (inc (count b)))) (range 0 (inc (count a)))))) [(count a) (count b)])))


  )
