(ns the-joy-of-clojure.vectors)

(vec (range 1 10))

;; The result will be the same type as the first argument of the into function
(into [1 2 3] '(:a :b :c)
(into '(1 2 3) #{:a :b :c})

;; Creating vectors of primitives is easy
(into (vector-of :int) [Math/PI 1 2.3])
 
(def a-to-j (vec (map char (range 65 75))))

;; First and last operate on seqs therefore, they are not efficient operations on vectors since
;; you have a boxing into a seq operation
(first a-to-j)
(rest a-to-j)

(get a-to-j 0)
(nth a-to-j 1)
;; Notice that vectors are functions that expect the index and return the value stored at that index
(a-to-j 2)
;; And they raise an IndexOutOfBoundsException
; (a-to-j 10)

;; This will return false since contains is about keys, in the case of vectors, keys are the indices.
(contains? a-to-j \A)





