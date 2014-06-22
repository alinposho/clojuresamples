(ns bookexamples.forclojure.compress-sequence)

(defn my-compress-sequence [xs]
	(map first (partition-by identity xs)))

(comment

(load-file "src/bookexamples/forclojure/compress-sequence.clj")
(refer 'bookexamples.forclojure.compress-sequence)

(my-compress-sequence [1 1 2 3 3 2 2 3])
(my-compress-sequence [[1 2] [1 2] [3 4] [1 2]])

;; Some of the solutions on the web
((fn [x] (remove nil? (map #(if (= %1 %2) nil %1) x (cons nil x))))
	[1 1 2 3 3 2 2 3])
)