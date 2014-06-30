(ns bookexamples.forclojure.pascal-trapezoid)

(defn pascal-trapezoid [xs]
	(iterate 
		(fn [[x & xs]] 
			(reduce 
				(fn [ys y] 
					(cons y (cons (+ y (first ys)) (rest ys))))  
				(list x x) xs)) xs))

(comment

(load-file "src/bookexamples/forclojure/pascal-trapezoid.clj")
(refer 'bookexamples.forclojure.pascal-trapezoid)

(second (pascal-trapezoid [2 3 2]))
(take 5 (pascal-trapezoid [1]))
(take 2 (pascal-trapezoid [3 1 2]))

;; Some of the solutions on the web

)