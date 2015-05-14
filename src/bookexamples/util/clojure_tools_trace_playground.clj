(ns bookexamples.util.clojure-tools-trace-playground
  (:require [clojure.tools.trace :refer :all]))

;; Experimenting with various clojure.tools.trace functions and macros

;; This will be our test subject
(defn sum [xs]
  (if (seq xs)
    (+ (first xs) (sum (rest xs)))
    0))

(comment

  (trace sum)
  (trace (sum (range 1 4)))

  (trace-vars sum);; This will cause the function sum to be traced

  (sum (range 1 4))

  (untrace-vars sum)
  )