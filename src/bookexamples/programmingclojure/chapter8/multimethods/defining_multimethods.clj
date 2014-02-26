(ns bookexamples.programmingclojure.chapter8.multimethods.defining-multimethods)

;; Defining a multimethod named my-print that dispatches based on the class of its first argument
(defmulti my-print class)

(defmethod my-print String [s]
  (.write *out* s))

(my-print "Hello")
;(my-print nil)

(defmethod my-print nil [n]
  (.write *out* "nil"))

(my-print nil)

;; Dispatch is inheritance aware
(defmethod my-print Number [n]
  (.write *out* (.toString n)))
;; Notice that my-print works with Integers which are subtypes of Number 
(my-print 8)
(my-print 8.0)

(isa? Integer Number)


