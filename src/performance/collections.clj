(ns performance.collections)

(def v (vec (range 0 10)))
(def a (byte-array (range 0 10000000)))
(def al (java.util.ArrayList.))
(dotimes [n 10000000] (.add al n))


(time (v 3))
(time (get 3 a))

(time (conj v -11))
(time (.get al 2))

;; Getting elements from ArrayLists seems to be slower than getting them from Clojure vectors.
(dotimes [_ 10] (time (conj v -11)))
(dotimes [_ 10] (time (.add al -11)))