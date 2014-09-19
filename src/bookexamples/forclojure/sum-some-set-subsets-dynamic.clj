(ns bookexamples.forclojure.sum-some-set-subsets-dynamic)

;; Problem 131: 
;; Given a variable number of sets of integers, create a function which returns true iff all of the sets have a non-empty subset with an equivalent summation.

;; Solution that does not work for large sets!!!
(defn some-subsets? [& sts]
  (letfn [(combinations [k st]
            "Generates all combinations of subsets of st of length k and less"
            (if (<= k 0) #{#{}}
                (set 
                  (for [s st
                        subst (combinations (dec k) st)
                        :let [nsubst (conj subst s)]]
                    nsubst))))
          (modified-combinations [st]
            "Add the initial set to each subset creating pair of (subset set)"
            (map #(list % st) (combinations (count st) st)))
          (all-combinations-mapped [sts]
            "All combinations of subsets grouped by subset sum"
            (group-by #(apply + (first %)) (mapcat modified-combinations sts)))
          (extract-initial-sets [sts]
            (set (map (fn [vct] (set (map second (val vct)))) sts)))
          (equiv-sum-sets [sts]
            (filter #(= (set sts) %) (extract-initial-sets (all-combinations-mapped sts))))]
    (not (empty? (equiv-sum-sets sts)))))

(defn sum-subset? [s x]
  (let [A (apply + (filter neg? x))
        B (apply + (filter pos? x))
        N (dec (count x))]
    (letfn [(Q [i s]
              (do 
                (println (str "A=" A "B=" B "i=" i "s=" s))
              (cond 
                (not (and (<= A s B) (<= 0 i N))) false
                ; :else (do (println (str "A=" A "B=" B "i=" i "s=" s)) true)))]
                (zero? s) (= (x 0) s)
                :else 
                      (or 
                        (Q (dec i) s) 
                        (= (x i) s) 
                        (Q (dec i) (- s (x i)))))))]
      (let [mQ (memoize Q)]
        (Q N s)))))


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




)