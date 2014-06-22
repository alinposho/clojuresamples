(ns bookexamples.forclojure.split-by-type)

(defn split-by-type [xs]
	(vals (group-by type xs)))

(comment

(load-file "src/bookexamples/forclojure/split-by-type.clj")
(refer 'bookexamples.forclojure.split-by-type)

(split-by-type [1 :a 2 :b 3 :c])
(split-by-type [:a "foo"  "bar" :b])
(split-by-type [[1 2] :a [3 4] 5 6 :b])


;; Some of the solutions on the web
(def f1  (comp vals (partial group-by type)))

)