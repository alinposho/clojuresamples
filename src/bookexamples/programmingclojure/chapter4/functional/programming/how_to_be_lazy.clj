(ns bookexamples.programmingclojure.chapter4.functional.programming.how-to-be-lazy)

;; bad idea
(defn stack-consuming-fibo
  "This stack implmentation will raise StackOverflowError for small numbers"
  [n]
  (cond
    (= n 0) 0
    (= n 1) 1
    :else (+ (stack-consuming-fibo (- n 2))
             (stack-consuming-fibo (- n 1)))))

(stack-consuming-fibo 0)
(stack-consuming-fibo 1)
(stack-consuming-fibo 2)
(stack-consuming-fibo 9)
(stack-consuming-fibo 10)
;; This will most likely cause a StackOverflowError
;(stack-consuming-fibo 10000)

;; Tail recursive fibo
(defn tail-fibo [n]
  (letfn [(fib [current next n]
               (if(= n 0) 
                 current
                 (fib (+ current next) (+ n) (dec n))))]
  (fib 0N 1N n)))

(tail-fibo 0)
(tail-fibo 1)
(tail-fibo 10)
(tail-fibo 100)
;; This will cause a StackOverflowError because Clojure does not have tail call optiomizations
;(tail-fibo 1000000)

;; Tail fibo that does not raise SOE

(defn recur-fibo [n]
  (letfn [(fib [current next n]
               (if(zero? n) 
                 current
                 (recur next (+ current next) (dec n))))]
  (fib 0N 1N n)))

(recur-fibo 0)
(recur-fibo 1)
(recur-fibo 10)
(recur-fibo 100)
;; This will not case a SOE as it did for the tail-fibo
;(recur-fibo 1000000)
;(recur-fibo 10000000)

;; Lazy sequence fibo
(defn lazy-seq-fibo 
  ([]
    (concat [0 1] (lazy-seq-fibo 0N 1N)))
  ([a b]
    (let [n (+ a b)]
      (lazy-seq 
        (cons n (lazy-seq-fibo b n))))))

(take 10 (lazy-seq-fibo))
(last (take 100 (lazy-seq-fibo)))
(nth (lazy-seq-fibo) 100)
(nth (lazy-seq-fibo) 10)
;(rem (nth (lazy-seq-fibo) 1000000) 1000)


;; An even shorter version of fibo
(defn fibo []
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))

(nth (fibo) 0)
(nth (fibo) 1)
(nth (fibo) 10)
(nth (fibo) 100)
;; Don't be stupid and call this since it will not stop if you evaluate the function
;(fibo)


;; This will return immediately, without calculating all those fibonacci numbers
(def lots-of-fibs (take 100000000 (fibo)))

;; This compute fibs only to 100 not the whole 100 million of them
(nth lots-of-fibs 100)

;; This will cause a GC error for large arguments since it's a def not a defn
(def head-fibo (lazy-cat [0N 1N] (map + head-fibo (rest head-fibo))))




