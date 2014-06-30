(ns bookexamples.forclojure.tree-into-tables)

(defn tree-into-tables [xs]
	(into {} 
		(for [[k v] xs
		  [inner-key inner-val] v] [[k inner-key] inner-val])))

(comment

(load-file "src/bookexamples/forclojure/tree-into-tables.clj")
(refer 'bookexamples.forclojure.tree-into-tables)

(tree-into-tables {:a {1 "s"} :b {2 "g" 3 "h"}})
(= (tree-into-tables '{a {p 1, q 2}
         b {m 3, n 4}})
   '{[a p] 1, [a q] 2
     [b m] 3, [b n] 4})

(= (tree-into-tables '{[1] {a b c d}
         [2] {q r s t u v w x}})
   '{[[1] a] b, [[1] c] d,
     [[2] q] r, [[2] s] t,
     [[2] u] v, [[2] w] x})

(= (tree-into-tables '{m {1 [a b c] 3 nil}})
   '{[m 1] [a b c], [m 3] nil})

;; Some of the solutions on the web

)