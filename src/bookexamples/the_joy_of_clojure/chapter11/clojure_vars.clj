(ns the-joy-of-clojure.clojure-vars)

;; This will return the value of the var
*read-eval*
;; While this will return the name of the var
(var *read-eval*)
#'*read-eval*; Similar to the call above

;; This is unlike the vars which return their value when you call them by name
(def r (ref 0))
;; Refs return their name when called by name
r
@r