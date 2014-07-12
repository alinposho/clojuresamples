(ns bookexamples.forclojure.reimplement-trampoline)

(defn my-trampoline [f & args]
	(loop [res (apply f args)]
		(if-not (fn? res) 
			res
			(recur (res)))))



(comment

(load-file "src/bookexamples/forclojure/reimplement-trampoline.clj")
(refer 'bookexamples.forclojure.reimplement-trampoline)

(my-trampoline 1)
(= (letfn [(triple [x] #(sub-two (* 3 x)))
          (sub-two [x] #(stop?(- x 2)))
          (stop? [x] (if (> x 50) x #(triple x)))]
    (my-trampoline triple 2))
  82)

(= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
          (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
    (map (partial my-trampoline my-even?) (range 6)))
  [true false true false true false])

;; Some of the solutions on the web
;; I don't want to know what this does
(fn [x] (let [gcd (comp (partial apply +) first (partial drop-while (comp (partial < 0) (partial apply min))) #(iterate (fn [[x1 x2]] (vector (mod x1 x2) (mod x2 x1))) [%1 %2]))]
     (if (= x 1) 1 (count (filter (comp (partial = 1) (partial gcd x)) (range 1 x)))))
)


)