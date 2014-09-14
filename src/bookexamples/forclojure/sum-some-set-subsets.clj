(ns bookexamples.forclojure.sum-some-set-subsets)

;; Problem 131: 
;; Given a variable number of sets of integers, create a function which returns true iff all of the sets have a non-empty subset with an equivalent summation.

;; Solution that does not work for large sets!!!
(defn some-subsets [& sts]
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



;; Solution that does not work for large sets!!!
(comment

(load-file "src/bookexamples/forclojure/sum-some-set-subsets.clj")
(refer 'bookexamples.forclojure.sum-some-set-subsets)

(list
  (= true  (some-subsets #{-1 1 99} 
               #{-2 2 888}
               #{-3 3 7777}))

  (= false (some-subsets #{1}
               #{2}
               #{3}
               #{4}))

  (= true  (some-subsets #{1}))

  (= false (some-subsets #{1 -3 51 9} 
               #{0} 
               #{9 2 81 33}))

  (= true  (some-subsets #{1 3 5}
               #{9 11 4}
               #{-3 12 3}
               #{-3 4 -2 10}))

  (= false (some-subsets #{-1 -2 -3 -4 -5 -6}
               #{1 2 3 4 5 6 7 8 9}))

  (= true  (some-subsets #{1 3 5 7}
               #{2 4 6 8}))

  (= true  (some-subsets #{-1 3 -5 7 -9 11 -13 15}
               #{1 -3 5 -7 9 -11 13 -15}
               #{1 -1 2 -2 4 -4 8 -8}))

  (= true  (some-subsets #{-10 9 -8 7 -6 5 -4 3 -2 1}
               #{10 -9 8 -7 6 -5 4 -3 2 -1}))
)

;; Some of the solutions on the web



;; Some trials
(filter #(= #{#{1 2 3 4 5} #{5 6}} %)
 (set (map (fn [vct] (set (map second (val vct))))
  (let [
        comb-st (fn [st]  (map #(list % st) (combinations (count st) st)))
        combs (mapcat comb-st sts)]
    (group-by #(apply + (first %)) combs))))))

(defn combinations [k st]
  (if (<= k 0) #{#{}}
      (set 
        (for [s st
              subst (combinations (dec k) st)
              :let [nsubst (conj subst s)]]
          nsubst))))

(let [st #{1 2 3 4}
      combs (combinations (count st) st)]
  (group-by #(apply + (first %)) (map #(list % st) combs)))

(let [sts [#{1 2 3 4} #{5 6}]
      comb-st (fn [st]  (map #(list % st) (combinations (count st) st)))
      combs (mapcat comb-st sts)]
      ; combs)
  (group-by #(apply + (first %)) combs))


; (filter #(= (hash-set (map second (val %))) (hash-set #{1 2 3 4 5} #{5 6}))
(filter #(= #{#{1 2 3 4 5} #{5 6}} %)
 (set (map (fn [vct] (set (map second (val vct))))
  (let [sts [#{1 2 3 4 5} #{5 6}]
        comb-st (fn [st]  (map #(list % st) (combinations (count st) st)))
        combs (mapcat comb-st sts)]
    (group-by #(apply + (first %)) combs)))))

)