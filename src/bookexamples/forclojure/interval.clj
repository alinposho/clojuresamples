(ns bookexamples.forclojure.interval)

;; Problem 171: Write a function that takes a sequence of integers and returns a sequence of "intervals". Each interval is a a vector of two integers, start and end, such that all integers between start and end (inclusive) are contained in the input sequence.
(defn interval [x]
  (reverse 
    (reduce 
      (fn [[[a b] & rs :as s] n] 
        (if (or (nil? b) (> n (inc b))) 
          (cons [n n] s)
          (cons [a n] rs)))
      []
      (sort x))))


(comment

(load-file "src/bookexamples/forclojure/interval.clj")
(refer 'bookexamples.forclojure.interval)

(= (interval [1 2 3]) [[1 3]])
(= (interval [10 9 8 1 2 3]) [[1 3] [8 10]])
(= (interval [1 1 1 1 1 1 1]) [[1 1]])
(= (interval []) [])
(= (interval [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11])
       [[1 4] [6 6] [9 11] [13 17] [19 19]])

;; Some of the solutions on the web
#(let [[y & z] (sort (set %))]
     (if y
       (reverse
         (reduce
           (fn [[[a b & _] & d] c]
             (if (= (inc b) c)
               (cons
                 [a c]
                 d)
               (list*
                 [c c]
                 [a b]
                 d)))
           [[y y]] z))
       []))

(fn intervals [x]
    (let [x (distinct (sort x))
          add (fn [[s t] x] (if (= 1 (- x t)) [s x] [x x]))]
        (map last (partition-by first (next (reductions add [0 (first x)] x))))))


)