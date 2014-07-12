(ns bookexamples.forclojure.equivalence-classes)
`
(defn eq-classes [f xs]
	(set (map set (vals (group-by f xs)))))


(comment

(load-file "src/bookexamples/forclojure/equivalence-classes.clj")
(refer 'bookexamples.forclojure.equivalence-classes)

(= (eq-classes #(* % %) #{-2 -1 0 1 2})
   #{#{0} #{1 -1} #{2 -2}})
(= (eq-classes #(rem % 3) #{0 1 2 3 4 5 })
   #{#{0 3} #{1 4} #{2 5}})
(= (eq-classes identity #{0 1 2 3 4})
   #{#{0} #{1} #{2} #{3} #{4}})
(= (eq-classes (constantly true) #{0 1 2 3 4})
   #{#{0 1 2 3 4}})

;; Some of the solutions on the web
(fn [func the-set]
  (->> (sort-by func the-set)
    (partition-by func)
    (map set)
    (into #{})))

(comp set (partial map set) vals group-by)


)