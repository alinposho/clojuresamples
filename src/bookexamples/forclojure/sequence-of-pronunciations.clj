(ns bookexamples.forclojure.sequence-of-pronunciations)

;; Problem 110: Write a function that returns a lazy sequence of "pronunciations" of a sequence of numbers. A pronunciation of each element in the sequence consists of the number of repeating identical numbers and the number itself. For example, [1 1] is pronounced as [2 1] ("two ones"), which in turn is pronounced as [1 2 1 1] ("one two, one one").
;;Your function should accept an initial sequence of numbers, and return an infinite lazy sequence of pronunciations, each element being a pronunciation of the previous element.
(defn prons [sq]
    (rest 
    	(iterate (fn [xs] (mapcat #(list (count %) (first %)) (partition-by identity xs)))
    	sq)))


(comment

(load-file "src/bookexamples/forclojure/sequence-of-pronunciations.clj")
(refer 'bookexamples.forclojure.sequence-of-pronunciations)

(apply hash-map (partition-by keyword? [:a 1 2 3 :b :c 4]))

(= [[1 1] [2 1] [1 2 1 1]] (take 3 (prons [1])))
(= [3 1 2 4] (first (prons [1 1 1 4 4])))
(= [1 1 1 3 2 1 3 2 1 1] (nth (prons [1]) 6))
(= 338 (count (nth (prons [3 2]) 15)))

;; Some of the solutions on the web
;; I don't want to know what this does
(fn base [x b] (if (< x b) [x] (conj (base (quot x b) b) (mod x b))))


)