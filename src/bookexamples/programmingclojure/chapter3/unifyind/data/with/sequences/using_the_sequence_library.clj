(ns bookexamples.programmingclojure.chapter3.unifyind.data.with.sequences.using-the-sequence-library)

(range 4)

(range 1 40)

(range 5 20 5)

(repeat 5 "x")

;;  This will cause the JVM to crash since repeat is infinite
; (repeat 5)

(take 20 (repeat 5))

(defn blah 
  ([x] (str "blah " x))
  ([x y] (str "blah " x " " y)))

(blah 4)
(blah 4 5)
  
(take 10 (iterate inc 1))
(take 10 (range 10))
(take 10 (cycle [1 2 3]))

;; Here we define a function that generates whole numbers
(defn whole-numbers [] 
  (iterate inc 1))

(take 20 (whole-numbers))

;; Here we bing the generator to a variable
(def whole-numbers (iterate inc 1))
;; So we don't need the parens around whole-numbers
(take 20 whole-numbers)

(interleave '(\a \b \c \d \e) whole-numbers)

(interpose \, '("apple" "banana" "pair"))
(apply str (interpose \, '("apple" "banana" "pair"))) ;; the same thing can be achieved with the clojure.string.join function

(use '[clojure.string :only (join)])
(join \, '("apple" "banana" "pair"))







