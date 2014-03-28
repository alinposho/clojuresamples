(ns the_joy_of_clojure.persistent-data-structures)

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