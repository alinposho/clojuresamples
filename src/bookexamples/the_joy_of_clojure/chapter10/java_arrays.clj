(ns the-joy-of-clojure.java-arrays)

(into-array [1 2 3])

;; This will raise an exception because the array is wrongly initialized as a Long[] array and the last
;; argument is a String
; (into-array [1 2 "5"])
;; However, this call will succeed
(into-array [(Object.) 1 2 "5"])

;; Notice that we need to transform the vector into an array in order to pass it to the 
;; String/format function that takes a variable number of arguments
(String/format "An int %d and a String %s" (to-array [99, "luftballons"]))
