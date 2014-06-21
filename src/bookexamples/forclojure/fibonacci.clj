(ns bookexamples.forclojure.fibonacci)

(defn my-fibonacci [n]
	(letfn [(fib [a b] (cons a (lazy-seq (fib b (+ b a)))))]
	(take n (fib 1 1))))


(comment

(load-file "src/bookexamples/forclojure/fibonacci.clj")
(refer 'bookexamples.forclojure.fibonacci)

(my-fibonacci 1)
(my-fibonacci 6)
(my-fibonacci 3)

;; Some of the solutions on the web
(def f1 (fn fibb [n] (take n (map first (iterate (fn [[l r]] [r (+ l r)]) [1 1])))))
(f1 6)

(#((apply comp (repeat (- % 2) (fn [x] (conj x (+ (peek x) (peek (pop x))))))) [1 1]) 8)

)