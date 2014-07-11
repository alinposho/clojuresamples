(ns bookexamples.forclojure.phi)

;; A function to compute Euler Phi totient function
(defn phi [n]
	(letfn [(gcd [a b]
            (if (< a b) 
          		(recur b a)
          		(first (filter #(= 0 (rem a %) (rem b %)) (iterate dec b)))))]
      (inc (count (filter #(= 1 (gcd % n)) (range 2 n))))))

(comment

(load-file "src/bookexamples/forclojure/phi.clj")
(refer 'bookexamples.forclojure.phi)

(phi 1)
(= (phi 10) (count '(1 3 7 9)) 4)
(= (phi 40) 16)
(= (phi 99) 60)

;; Some of the solutions on the web
;; I don't want to know what this does
(fn [x] (let [gcd (comp (partial apply +) first (partial drop-while (comp (partial < 0) (partial apply min))) #(iterate (fn [[x1 x2]] (vector (mod x1 x2) (mod x2 x1))) [%1 %2]))]
     (if (= x 1) 1 (count (filter (comp (partial = 1) (partial gcd x)) (range 1 x)))))
)


)