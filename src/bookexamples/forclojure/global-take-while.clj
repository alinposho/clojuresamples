(ns bookexamples.forclojure.global-take-while)

;; Problem 114: take-while is great for filtering sequences, but it limited: you can only examine a single item of the sequence at a time. What if you need to keep track of some state as you go over the sequence?
;;              Write a function which accepts an integer n, a predicate p, and a sequence. It should return a lazy sequence of items in the list up to, but not including, the nth item that satisfies the predicate.
(defn gtake-while [n p s]
	(let [[l r] (split-with (complement p) s)]
	  (if (= 1 n) 
	  	l
	  	(concat 
				l 
	  		(conj (gtake-while (dec n) p (rest r)) (first r))))))

(comment

(load-file "src/bookexamples/forclojure/global-take-while.clj")
(refer 'bookexamples.forclojure.global-take-while)

(= [2 3 5 7 11 13]
   (gtake-while 4 #(= 2 (mod % 3))
         [2 3 5 7 11 13 17 19 23]))
(= ["this" "is" "a" "sentence"]
   (gtake-while 3 #(some #{\i} %)
         ["this" "is" "a" "sentence" "i" "wrote"]))
(= ["this" "is"]
   (gtake-while 1 #{"a"}
         ["this" "is" "a" "sentence" "i" "wrote"]))

;; Some of the solutions on the web
;; I don't want to know what this does
(fn f
  ([n p [x & xs :as xxs]]
   (if (zero? (dec n))
     (take-while (complement p) xxs)
     (cons x (f (if (p x) (dec n) n) p xs)))) 
  )
  
(fn global-take-while [n f coll]
  (let [first-ele (first coll)]
    (if (f first-ele)
      (if (> n 1)
        (cons first-ele (global-take-while (dec n) f (rest coll))))
      (cons first-ele (global-take-while n f (rest coll))))))
)