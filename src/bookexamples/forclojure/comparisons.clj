(ns bookexamples.forclojure.comparisons)

(defn comparisons [f x y]
	(cond (f x y) :lt
		  (f y x) :gt
		  :else :eq))

(comment

(load-file "src/bookexamples/forclojure/comparisons.clj")
(refer 'bookexamples.forclojure.comparisons)

(= :gt (comparisons < 5 1))
(= :eq (comparisons (fn [x y] (< (count x) (count y))) "pear" "plum"))
(= :lt (comparisons (fn [x y] (< (mod x 5) (mod y 5))) 21 3))
(= :gt (comparisons > 0 2))

;; Some of the solutions on the web
(def f1 
	(fn [< x y] 
	  (if (< x y) :lt 
		(if (< y x) :gt :eq))))
(f1 (fn [x y] (< (count x) (count y))) "pear" "plum")


)