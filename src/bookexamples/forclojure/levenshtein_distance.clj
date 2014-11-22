(ns bookexamples.forclojure.levenshtein-distance)

;;; Problem 101:
;;  Given two sequences x and y, calculate the Levenshtein distance of x and y, i. e. the minimum number of edits needed to transform x into y. The allowed edits are:
;;   - insert a single item
;;   - delete a single item
;;   - replace a single item with another item
;;  Function memoization should make the simple algorithm work like a dynamic programming algorith(see the memoization example)
(def levenshtein
  (memoize
    (fn [xs ys]
      (cond (zero? (count xs)) (count ys)
            (zero? (count ys)) (count xs)
            :else (min
                    (inc (levenshtein (rest xs) ys))
                    (inc (levenshtein xs (rest ys)))
                    (+ (levenshtein (rest xs) (rest ys))
                       (if (= (first xs) (first ys)) 0 1)))))))

(comment

  (load-file "src/bookexamples/forclojure/levenshtein_distance.clj")
  (refer 'bookexamples.forclojure.levenshtein-distance)

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

  )
