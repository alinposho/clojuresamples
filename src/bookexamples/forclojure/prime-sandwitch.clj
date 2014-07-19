(ns bookexamples.forclojure.prime-sandwitch)

;; Problem #116: A balanced prime is a prime number which is also 
;; the mean of the primes directly before and after it in the sequence 
;; of valid primes. Create a function which takes an integer n, and returns true iff it is a balanced prime.
(defn balanced-prime? [n]
  (letfn [(prime? [x] (and (> x 1) (not-any? #(zero? (rem x %1)) (range 2 x))))
          (second-prime [xs] (second (filter prime? xs)))
          (mean [a b] (/ (+ a b) 2))]
    (let [prev-prime (second-prime (iterate inc n))
          next-prime (second-prime (range n 0 -1))]
      (and (> n 2) (prime? n) (= n (mean prev-prime next-prime))))))

(comment

(load-file "src/bookexamples/forclojure/prime-sandwitch.clj")
(refer 'bookexamples.forclojure.prime-sandwitch)

(balanced-prime? 4)
(balanced-prime? 563)
(= 1103 (nth (filter balanced-prime? (range)) 15))
(take 15 (filter balanced-prime? (range)))



;; Some of the solutions on the web
;; I don't want to know what this does
(fn [num]
  (letfn [(prime []
            (letfn [(step [coll]
                      (let [head (first coll)]
                        (lazy-seq (cons head (step (filter #(pos? (mod % head)) coll))))))]
              (step (range 2 Long/MAX_VALUE))))
          (balanced-prime []
            (map second (filter #(= (second %) (/ (+ (first %) (last %)) 2)) (partition 3 1 (prime)))))]
    (= num (last (take-while #(<= % num) (balanced-prime))))))

)