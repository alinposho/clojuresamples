(ns bookexamples.forclojure.tree)

(defn tree? [xs] 
	(if (and (coll? xs) (= (count xs) 3)) 
			(and (tree? (second xs)) (tree? (last xs)))
			(nil? xs)))

(comment

(load-file "src/bookexamples/forclojure/tree.clj")
(refer 'bookexamples.forclojure.tree)

(tree? '(:a (:b nil nil) nil))
(tree? [1 nil [2 [3 nil nil] [4 nil nil]]])
(tree? [1 [2 [3 [4 false nil] nil] nil] nil])


;; Some of the solutions on the web
(fn is-tree [x] (or (nil? x) (and
    (sequential? x)
    (= 3 (count x))
    (is-tree (nth x 1))
    (is-tree (nth x 2)))))

)