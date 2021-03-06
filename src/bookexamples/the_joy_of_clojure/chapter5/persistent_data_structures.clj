(ns the-joy-of-clojure.persistent-data-structures)

(def ds (into-array [:willie :barnabas :adam]))
; (seq ds)

;; Chnages to the array happen in place
(aset ds 1 :qunetin)

;; Equality for sequences works no matter their type, if elements are in the same order
(= [1 2 3] '(1 2 3))
;; However Set is not a sequence therefore, these are not equal
(= [1 2 3] #{1 2 3})

;; 
(class (hash-map :q 1))
(class (seq (hash-map :q 1)))
(seq (keys (hash-map :q 1)))
(class (seq (keys (hash-map :q 1))))

;; list* prepends the arguments to the last argument, treated as a sequence
(list* [1 2] '(3 4))

;; zip and zipmap examples

;; This will create a map
(zipmap [1 2 3 4] '(:a :b :c :d :e))

(interleave [1 2 3 4] '(:a :b :c :d :e)) ;; Notice that the last element of the second collection is ignored


