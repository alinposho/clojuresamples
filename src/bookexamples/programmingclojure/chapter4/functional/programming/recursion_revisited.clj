(ns bookexamples.programmingclojure.chapter4.functional.programming.recursion-revisited)

(trampoline list)
(trampoline + 1 2)

;; Example only, don't write code like this
(defn trampoline-fibo [n]
  (let [fib (fn fib [f-2 f-1 current]
              (let [f (+ f-2 f-1)]
              (if (= n current)
              f
              #(fib f-1 f (inc current)))))]
    (cond
      (= n 0) 0
      (= n 1) 1
      :else (fib 0N 1 2))))

(trampoline trampoline-fibo 10N)


