(ns bookexamples.forclojure.identify-keys-and-values)

;; Problem 105: Given an input sequence of keywords and numbers, create a map such that 
;; each key in the map is a keyword, and the value is a sequence of all the numbers (if any) 
;; between it and the next keyword in the sequence.
(defn to-map [xs]
    (apply hash-map 
    	(mapcat #(if (keyword? (first %1)) (interpose '() %1) (list %1)) (partition-by keyword? xs))))


(comment

(load-file "src/bookexamples/forclojure/identify-keys-and-values.clj")
(refer 'bookexamples.forclojure.identify-keys-and-values)

(apply hash-map (partition-by keyword? [:a 1 2 3 :b :c 4]))

(= {} (to-map []))
(= {:a [1]} (to-map [:a 1]))
(= {:a [1], :b [2]} (to-map [:a 1, :b 2]))
(= {:a [1 2 3], :b [], :c [4]} (to-map [:a 1 2 3 :b :c 4]))


;; Some of the solutions on the web
;; I don't want to know what this does
(fn f 
  ([x] (f x {}))
  ([[k & r] a]
     (if k
       (let [[v n] (split-with number? r)]
        (f n (conj a [k v])))
       a)))

(fn [x] (apply hash-map (map 
    #(if (keyword? (first %)) (first %) (keep identity %)) 
    (partition-by #(when (keyword? %) %) (interpose nil x)))))



)