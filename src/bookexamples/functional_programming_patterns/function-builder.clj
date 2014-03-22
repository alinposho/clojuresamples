(ns bookexamples.functional_programming_patterns.function-builder)

(def person {:address {:street {:name "Fake Street"}}})

(get-in person [:address :street :name])

;; And now for some function composition

(defn append-a [s] (str s "a"))
(defn append-b [s] (str s "b"))
(defn append-c [s] (str s "c"))

(def append-cba (comp append-a append-b append-c))