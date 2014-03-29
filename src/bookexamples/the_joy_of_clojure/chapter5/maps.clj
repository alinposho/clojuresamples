(ns the-joy-of-clojure.maps)
;; Maps can be created using the hash-map function
(hash-map :a 1. :b 2, "d" :p)

;; Or like so
{:a 1. :b 2, "d" :p}

;; Different ways of creating maps
(zipmap [:a :b] [1 2])
(zipmap [:a :b] '(1 2))

(apply hash-map [:a 1 :b 2])

;; The difference between hash maps and sorted maps in clojure
{1 :int 1.0 :float} ;; This will hold both keys
(sorted-map 1 :int 1.0 :float) ;; This will hold only one of the keys