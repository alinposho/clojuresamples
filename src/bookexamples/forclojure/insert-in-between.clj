(ns bookexamples.forclojure.insert-in-between)

;; Problem 132: Write a function that takes a two-argument predicate, a value, and a collection; and returns a new collection where the value is inserted between every two items that satisfy the predicate.
(defn insert-in-between [p elem [x & xs :as s]]
  (cond 
    (or (nil? x) (nil? (first xs))) s
    :else (concat 
            (if (p x (first xs)) [x elem] [x]) 
            (lazy-seq (insert-in-between p elem xs)))))

;; This does not work for lazy seqs and it's not complete
(reduce concat (map (fn [[a b]](if (< a b) [a :less b] [a b])) (partition 2 1 [1 6 7 4 3])))

(comment

(load-file "src/bookexamples/forclojure/insert-in-between.clj")
(refer 'bookexamples.forclojure.insert-in-between)

(= '(1 :less 6 :less 7 4 3) (insert-in-between < :less [1 6 7 4 3]))
(= '(2) (insert-in-between > :more [2]))
(= [0 1 :x 2 :x 3 :x 4]  (insert-in-between #(and (pos? %) (< % %2)) :x (range 5)))
(empty? (insert-in-between > :more ()))
(= [0 1 :same 1 2 3 :same 5 8 13 :same 21]
   (take 12 (->> [0 1]
                 (iterate (fn [[a b]] [b (+ a b)]))
                 (map first) ; fibonacci numbers
                 (insert-in-between (fn [a b] ; both even or both odd
                       (= (mod a 2) (mod b 2)))
                     :same))))

;; Some of the solutions on the web
;; I don't want to know what this does
(fn i [p a c]                                                                                                                
    (lazy-seq                                                                                                                  
      (let [[f s & t] c
            r (i p a (rest c))                                                                                                 
            e cons]                                                                                                            
        (if s                                                                                                                  
          (e f                                                                                                                 
             (if (p f s)                                                                                                       
               (e a r)                                                                                                         
               r))                                                                                                             
          c))))

(fn inbetween [f x s] (let [
    pairs (partition 2 1 s)
    ins (cons nil (for [i pairs] (when (apply f i) x)))
    insed (interleave ins s)]
    (keep identity insed)))



)