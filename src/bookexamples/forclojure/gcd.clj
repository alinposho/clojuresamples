(ns bookexamples.forclojure.gcd)

;; Solution no 2
(defn gcd [a b]
	(if (< a b) 
		(recur b a)
		(first (filter #(= 0 (rem a %) (rem b %)) (iterate dec b)))))

;; Solution no 1
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
(fn my-common-divisor [x y]
  (loop [n (min x y)]
    (when (< n (+ (min x y) 1))
      (if (and (= (mod x n) 0) (= (mod y n) 0))
        n
  (recur (- n 1)))))
(comp (partial apply +) first (partial drop-while (comp (partial < 0) (partial apply min))) #(iterate (fn [[x1 x2]] (vector (mod x1 x2) (mod x2 x1))) [%1 %2]))

)