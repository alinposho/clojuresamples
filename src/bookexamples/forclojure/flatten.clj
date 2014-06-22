(ns bookexamples.forclojure.flatten)

;; First go
(defn my-flatten [xs]
	(reduce (fn [rs x] (if (coll? x) (concat rs (my-flatten x)) (conj rs x))) [] xs))

;; Second go
(defn my-flatten [xs]
	(mapcat (fn [rs] (if (coll? rs) (my-flatten rs) (list rs))) xs))

;; Third go
(defn my-flatten [xs]
	(mapcat #(if (coll? %) (my-flatten %) (list %)) xs))


(comment

(load-file "src/bookexamples/forclojure/flatten.clj")
(refer 'bookexamples.forclojure.flatten)

(my-flatten [1 2 1])
(my-flatten [1 [2 [4 '(5 [6 (7)])]]])
(my-flatten '((((:a)))))

;; Some of the solutions on the web
((fn my-flatten [x]
  (filter (complement sequential?)
    (rest (tree-seq sequential? seq x)))) [1 [2 [4 '(5 [6 (7)])]]])

)