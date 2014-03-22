(ns bookexamples.functional_programming_patterns.function-builder)

(def person {:address {:street {:name "Fake Street"}}})

(get-in person [:address :street :name])