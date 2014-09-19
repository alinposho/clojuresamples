(ns bookexamples.forclojure.sum-some-set-subsets-dynamic)

;; Problem 131: 
;; Given a variable number of sets of integers, create a function which returns true if all of the sets have a non-empty subset with an equivalent summation.

;; Solution that does not work for large sets!!!
(defn some-subsets? [& xs] 
  (let [A (apply + (filter neg? (first xs)))
        B (apply + (filter pos? (first xs)))
        sum-subset? (fn [s x]
                      (let [A (apply + (filter neg? x))
                            B (apply + (filter pos? x))
                            N (dec (count x))]
                        (letfn [(Q [i s]
                                  (cond 
                                    (not (and (<= A s B) (<= 0 i N))) false
                                    (zero? i) (= (x 0) s)
                                    :else (or 
                                            (= (x i) s)
                                            (Q (dec i) s) 
                                            (Q (dec i) (- s (x i))))))]
                            ((memoize Q) N s))))
        all-sets-have-sum? (fn [s] 
                              (every? true? 
                                  (map #(sum-subset? s (vec %)) xs)))]
    (not-every? false? 
      (map all-sets-have-sum? (range A (inc B))))))


(defn sum-subset? [s x]
  (let [A (apply + (filter neg? x))
        B (apply + (filter pos? x))
        N (dec (count x))]
    (letfn [(Q [i s]
              (cond 
                (not (and (<= A s B) (<= 0 i N))) false
                (zero? i) (= (x 0) s)
                :else (or 
                        (= (x i) s)
                        (Q (dec i) s) 
                        (Q (dec i) (- s (x i))))))]
        ((memoize Q) N s))))




;; Solution that does not work for large sets!!!
(comment

(load-file "src/bookexamples/forclojure/sum-some-set-subsets-dynamic.clj")
(refer 'bookexamples.forclojure.sum-some-set-subsets-dynamic)

(list
  (= true  (some-subsets? #{-1 1 99} 
               #{-2 2 888}
               #{-3 3 7777}))

  (= false (some-subsets? #{1}
               #{2}
               #{3}
               #{4}))

  (= true  (some-subsets? #{1}))

  (= false (some-subsets? #{1 -3 51 9} 
               #{0} 
               #{9 2 81 33}))

  (= true  (some-subsets? #{1 3 5}
               #{9 11 4}
               #{-3 12 3}
               #{-3 4 -2 10}))

  (= false (some-subsets? #{-1 -2 -3 -4 -5 -6}
               #{1 2 3 4 5 6 7 8 9}))

  (= true  (some-subsets? #{1 3 5 7}
               #{2 4 6 8}))

  (= true  (some-subsets? #{-1 3 -5 7 -9 11 -13 15}
               #{1 -3 5 -7 9 -11 13 -15}
               #{1 -1 2 -2 4 -4 8 -8}))

  (= true  (some-subsets? #{-10 9 -8 7 -6 5 -4 3 -2 1}
               #{10 -9 8 -7 6 -5 4 -3 2 -1}))
)

;; Some of the solutions on the web
(fn [& sets]
  (not=
    nil
    (seq
      (reduce
        #(set (filter (fn [ele] (%1 ele)) %2))
        (map
          (fn [s]
            (set
              (map
                #(reduce + %)
                (filter
                  #(seq %)
                  (reduce #(concat %1 (map (fn [i] (set (conj i %2))) %1)) #{#{}} s)))))
          sets)))))

(fn common [& x] (letfn [
    (subset-sums [s] (if (= 1 (count s)) (set s) (let [
        s1 (first s)
        ssn (subset-sums (next s))
        ss1 (map (partial + s1) ssn)]
        (set (concat ssn ss1 [s1])))))]
    (boolean (seq (apply clojure.set/intersection (map subset-sums x))))))



)