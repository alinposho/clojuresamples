(ns bookexamples.functional_programming_patterns.partial-functions)

(defn add-two-ints [a b] (+ a b))

(def add-42 (partial add-two-ints 42))

;; The interesting part is that you can set only the first argument

(defn concat-2 [string number] (str (.toUpperCase string) ", " (+ number 1)))

(def concat-number (partial concat-2 78))
;; This will fail since the actual partial funtion is of type (number => string) not (string => string)
;; (concat-number "alin")

(def concat-string (partial concat-2 "some sting"))
(concat-string 89)
