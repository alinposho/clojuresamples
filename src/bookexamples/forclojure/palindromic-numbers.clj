(ns bookexamples.forclojure.palindromic-numbers)

;; Problem 150: 
;; A palindromic number is a number that is the same when written forwards or backwards (e.g., 3, 99, 14341).
;; Write a function which takes an integer n, as its only argument, and returns an increasing lazy sequence of all palindromic numbers that are not less than n.
;; The most simple solution will exceed the time limit!
(defn palindroms [n]
  (fn [params]
    (eval `(let [~@(flatten (vec params))] ~body))))

(comment

(load-file "src/bookexamples/forclojure/palindromic-numbers.clj")
(refer 'bookexamples.forclojure.palindromic-numbers)


;; Some of the solutions on the web

)