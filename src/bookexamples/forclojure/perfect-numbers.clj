(ns bookexamples.forclojure.perfect-numbers)

(defn perfect? [n]
	(letfn [(divisors [n]
            (filter #(zero? (mod n %)) (range 1 n)))]
    (= n (reduce + (divisors n)))))


;; More legible version
(defn divisors [n]
  (filter #(zero? (mod n %)) (range 1 n)))

;; Improved version
(defn divisors [n]
  (cons 1 (filter #(zero? (mod n %)) (range 2 (inc (quot n 2))))))

(divisors 6)

(comment

(load-file "src/bookexamples/forclojure/perfect-numbers.clj")
(refer 'bookexamples.forclojure.perfect-numbers)


(= (perfect? 6) true)
(= (perfect? 7) false)
(= (perfect? 496) true)
(= (perfect? 500) false)
(= (perfect? 8128) true)

;; Some of the solutions on the web
(fn [x]
    (letfn [(factors [x]
              (loop [xs [] i 1]
                (if (> (* i i) x)
                  (vec (sort (distinct xs)))
                  (if (zero? (rem x i))
                    (recur (conj xs i (/ x i)) (inc i))
                    (recur xs (inc i))))))]
      (= x (reduce + (pop (factors x))))))

(fn [x] (= x (apply + (filter #(= 0 (mod x %)) (range 1 x)))))


)