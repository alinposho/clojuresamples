(ns bookexamples.forclojure.penultimate-element)

(defn penultimate [coll]
	(let [xs (seq coll)]
	(if (empty? (rest (rest xs))) 
		(first xs)
		(recur (rest xs)))))


(comment


(load-file "src/bookexamples/forclojure/penultimate_element.clj")
(refer 'bookexamples.forclojure.penultimate-element)

(penultimate '(1 2 3))
(penultimate (list 1 2 3 4 5))

;; solutions on the web
((comp first next reverse) (list 1 2 3 4 5))
((comp peek pop vec) [[1 2] [3 4]])
(#(second (reverse %)) (list 1 2 3 4 5))
)