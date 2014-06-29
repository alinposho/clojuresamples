(ns bookexamples.forclojure.primes)

(defn primes 
	([n] (take n (primes [] 2)))
	([xs x]
	(let [prime? (not-any? #(zero? (rem x %1)) (range 2 x))]
		(if prime? 
			(cons x (lazy-seq (primes (conj xs x) (inc x))))
			(lazy-seq (primes xs (inc x)))))))


(comment

(load-file "src/bookexamples/forclojure/primes.clj")
(refer 'bookexamples.forclojure.primes)

(primes 5)
(last (primes 100))
(last (primes 1001))

;; Some of the solutions on the web
;; This will not work for n > 1000
(def f1 
  (fn [n]
	  (take n(filter
				(fn is-prime [n]
				  (nil?
				  (some
				    #(zero? (mod n %))
				    (range 2 n))))
				(range 2 1000)))))

;; This will not work for large values of n, i.e. n > 1000. It will take a lot of time to compute n
(fn [x] (take x (filter #(= (inc (mod (apply * (range 1N %)) %)) %) (iterate inc 2))))

)