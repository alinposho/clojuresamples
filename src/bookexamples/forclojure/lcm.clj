(ns bookexamples.forclojure.lcm)

(defn lcm [& X]
	  (loop [Xk (vec X)]
	    (if (apply = Xk) 
	      (first Xk)
	      (let [xkm (apply min Xk)
	      		xkm-index (.indexOf Xk xkm)
	      		xk0 (nth X xkm-index)
	      		xkm+1 (+ xkm xk0)]
		      (recur (assoc Xk xkm-index xkm+1))))))

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
(fn lcm [x1 & xn] (first (drop-while 
    (fn [s] (not-every? #(zero? (mod s %)) xn))
    (iterate (partial + x1) x1))))

)