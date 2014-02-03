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

(take 10 (filter even? whole-numbers))

(take-while (complement #{\a\e\i\o\u}) "the-quick-brown-fox")
(drop-while (complement #{\a\e\i\o\u}) "the-quick-brown-fox")

(split-at 5 (range 10))
(split-with #(<= % 10) (range 0 20 2))

(map #(format "<p>%s</p>" %) ["the" "quick" "brown" "fox"])


(reduce + (range 1 11))

(flatten [1 2 '(30 {:a 3, :b 4, :c #{:k :l}})]) ;; does not go recursively, instead it flattens only the first level

;; This is how one defines the flatMap function that is so prevalent in Scala collections
(defn flat-map [f x]
  (flatten (map f x)))

(flat-map #(range %) (range 5 10))

(for [word ["the" "quick" "brown" "fox"]]
  (format "<p>%s<p>" word))

(take 10 (for [n whole-numbers :when (even? n)] n))

(for [n whole-numbers :while (even? n)] n)

(for [file "ABCDEFGH" rank (range 1 9)]
  (format "%c%d" file rank))

;; Flawed. For some reason it does not work and I do not, yet, have the skill to make it work
(def primes(concat  [2 3 5 7] 
   (lazy-seq   (let [primes-from     (fn primes-from [n [f & r]]       (if (some #(zero? (rem n %))         (take-while #(<= (* % %) n) primes))         (recur (+ n f) r)         (lazy-seq (cons n (primes-from (+ n f) r))))) 
      wheel (cycle[2 4 2 4 6 2 6 4 2 4 6 6 2 6 4 2 
                   6 4 6 8 4 2 4 2 4 8 6 4 6 2 4 6                   2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]            (primes-from 11 wheel)))))


(take 10 (primes))
(def ordinals-and-primes (map vector (iterate inc 1) primes))
(take 10 (ordinals-and-primes))


(def x (for [i (range 1 3)] (do (println i) i)))
x
(doall x)






