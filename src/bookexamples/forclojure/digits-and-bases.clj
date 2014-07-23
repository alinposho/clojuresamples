(ns bookexamples.forclojure.digits-and-bases)

;; Problem 137: Write a function which returns a sequence of digits of a non-negative 
;; number (first argument) in numerical system with an arbitrary base (second argument). 
;; Digits should be represented with their integer values, 
;; e.g. 15 would be [1 5] in base 10, [1 1 1 1] in base 2 and [15] in base 16. 
(defn to-base [n base]
    (loop [xs (list (mod n base))
            n (quot n base)]
      (if (zero? n) xs
        (recur (cons (mod n base) xs) (quot n base)))))


(comment

(load-file "src/bookexamples/forclojure/digits-and-bases.clj")
(refer 'bookexamples.forclojure.digits-and-bases)

(apply hash-map (partition-by keyword? [:a 1 2 3 :b :c 4]))

(= [1 2 3 4 5 0 1] (to-base 1234501 10))
(= [0] (to-base 0 11))
(= [16 18 5 24 15 1] (to-base Integer/MAX_VALUE 42))

;; Some of the solutions on the web
;; I don't want to know what this does
(fn base [x b] (if (< x b) [x] (conj (base (quot x b) b) (mod x b))))


)