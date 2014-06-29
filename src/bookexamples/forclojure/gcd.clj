(ns bookexamples.forclojure.gcd)

(defn gcd [a b]
	(if (< a b) 
		(recur b a)
		(let [r (rem a b)]
			(if (zero? r) b (recur b r)))))

(comment

(load-file "src/bookexamples/forclojure/gcd.clj")
(refer 'bookexamples.forclojure.gcd)

(gcd 4 2)
(gcd 7 5)
(gcd 100 30)

;; Some of the solutions on the web

)