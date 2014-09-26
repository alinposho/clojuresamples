(ns bookexamples.forclojure.evaluation)

;; This will not compile if you uncomment the foo and bar definitions since defn creates global bindings
(defn outer [xs]
    #_(defn foo [a] 
        (if (zero? a) 
            a
            (bar (dec a))))
    #_(defn bar [a] 
        (if (zero? a) 
            a
            (foo (dec a))))
    )

(def a 100)

(defn rebind-a []
  (def a 1000) ;; this binding will change the global definition of a to 1000 even outside the scope of this function
  (println "a=" a)
  a)


(comment

(load-file "src/bookexamples/sicp/evaluation.clj")
(refer 'bookexamples.forclojure.palindrome)


)