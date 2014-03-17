(ns bookexamples.programmingclojure.chapter8.multimethods.moving-beyond-simple-dispatch
  (:require [clojure.string :as str]
            [bookexamples.programmingclojure.chapter8.multimethods.defining-multimethods :refer :all]))


(defmethod my-print java.util.Collection [c]
  (.write *out* "(")
  (.write *out* (str/join " " c))
  (.write *out* ")"))

;(my-print [8 9 10])
;(my-print '(8 9 10))

(defmethod my-print clojure.lang.IPersistentVector [c]
  (.write *out* "[")
  (.write *out* (str/join " " c))
  (.write *out* "]"))

;; This will raise an exception since the program cannot determine which methof to pick
;; the one that accepts a Collection or the one that takes an IPersistentVector
;(my-print [8 9 10])

;; But this will fix our little problem
(prefer-method my-print clojure.lang.IPersistentVector java.util.Collection)

;(my-print [8 9 10])
