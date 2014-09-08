(ns bookexamples.forclojure.insert-in-between)

;; Problem 132: Write a function that takes a two-argument predicate, a value, and a collection; and returns a new collection where the value is inserted between every two items that satisfy the predicate.
(defn insert-in-between [n p s]
  )

;; This does not work for lazy seqs and it's not complete
(reduce concat (map (fn [[a b]](if (< a b) [a :less b] [a b])) (partition 2 1 [1 6 7 4 3])))

(comment

(load-file "src/bookexamples/forclojure/insert-in-between.clj")
(refer 'bookexamples.forclojure.insert-in-between)

(= [2 3 5 7 11 13]
   (insert-in-between 4 #(= 2 (mod % 3))
         [2 3 5 7 11 13 17 19 23]))
(= ["this" "is" "a" "sentence"]
   (insert-in-between 3 #(some #{\i} %)
         ["this" "is" "a" "sentence" "i" "wrote"]))
(= ["this" "is"]
   (insert-in-between 1 #{"a"}
         ["this" "is" "a" "sentence" "i" "wrote"]))

;; Some of the solutions on the web
;; I don't want to know what this does
(fn f
  ([n p [x & xs :as xxs]]
   (if (zero? (dec n))
     (take-while (complement p) xxs)
     (cons x (f (if (p x) (dec n) n) p xs)))) 
  )
  
(fn insert-in-between [n f coll]
  (let [first-ele (first coll)]
    (if (f first-ele)
      (if (> n 1)
        (cons first-ele (insert-in-between (dec n) f (rest coll))))
      (cons first-ele (insert-in-between n f (rest coll))))))
)