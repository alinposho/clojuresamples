(ns bookexamples.programmingclojure.chapter8.multimethods.multimethods)

;; Living without muyltimethods
(defn my-print [expr]
  (.write *out* expr))

(my-print "hello")

(defn my-println [expr]
    (my-print expr)
    (my-print "\n"))

(my-println "Hello")
;; This will raise a NullPointerException
;(my-println nil)

(defn my-print [ob] 
  (cond
    (nil? ob) (.write *out* "nil")
    (string? ob) (.write *out* ob)))

(my-println "Hello")
(my-println nil)

;; This will not raise an exception but it will not print the vector either
(my-println [1 2 3])

