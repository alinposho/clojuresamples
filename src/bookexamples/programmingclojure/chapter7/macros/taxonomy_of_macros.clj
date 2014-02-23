(ns bookexamples.programmingclojure.chapter7.macros.taxonomy-of-macros)

;; Postponing evaluation

;; Sometimes the only way to postpone evaluation is to define a macro
(def slow-calc (delay (java.lang.Thread/sleep 1000) "Done!"))
;; The first time you evaluate slow-calc it will take at least one second, however,
;; all subsequent calls return immediately
(force slow-calc)


;; Wrapping evaluation

(with-out-str (print "Hello,") (print " world"))

(defmacro my-with-out-str [& expr]
  `(let [s# (new java.io.StringWriter)]
     (binding [*out* s#]
       ~@expr)
     (str s#)))

(my-with-out-str (print "Hello,") (print " world"))

(assert (= 1 1))

;(assert (= 1 3))



