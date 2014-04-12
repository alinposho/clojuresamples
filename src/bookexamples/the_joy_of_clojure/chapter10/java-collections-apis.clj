(ns the-joy-of-clojure.java-apis)

;; Using the java.util.List.get method on vectors
(.get '[1 2 3] 1)
(.get [1 2 3] 1)

;; This will raise an exception since Clojure lists do not implement the java.util.List.get method
; ((.get '(1 2 3) 1))

(ancestors (class #()))
(ancestors (class [1]))
(ancestors (class '(1)))
