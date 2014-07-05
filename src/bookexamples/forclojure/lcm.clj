(ns bookexamples.forclojure.lcm)

(defn lcm 
	([a b]
		(letfn [(gcd [a b]
					(if (< a b) 
						(recur b a)
						(first (filter #(= 0 (rem a %) (rem b %)) (iterate dec b)))))]
		(/ (* a b) (gcd a b))))
	([& bs] 
		(apply min (map #(apply lcm) (partition 2 1 bs)))))


(comment

(load-file "src/bookexamples/forclojure/lcm.clj")
(refer 'bookexamples.forclojure.lcm)

(lcm 2 3)
(== (lcm 2 3) 6)
(== (lcm 5 3 7) 105)
(== (lcm 1/3 2/5) 2)
(== (lcm 3/4 1/6) 3/2)
(== (lcm 7 5/7 2 3/5) 210)


;; Some of the solutions on the web
(comp (partial apply distinct?)
        (partial apply concat))

#(apply distinct? (apply concat %))

)