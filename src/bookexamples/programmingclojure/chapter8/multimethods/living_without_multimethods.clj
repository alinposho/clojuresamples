(ns bookexamples.programmingclojure.chapter8.multimethods.living-without-multimethods
  [:require [clojure.string :as str]])

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

(defn my-print-vector [ob] 
  (.write *out* "[")
  (.write *out* (str/join ", " ob))
  (.write *out* "]"))

(defn my-print [ob] 
  (cond
    (nil? ob) (.write *out* "nil")
    (string? ob) (.write *out* ob)
    (vector? ob) (my-print-vector ob)))
(my-println "Hello")
(my-println nil)
(my-println [1 2 3])
