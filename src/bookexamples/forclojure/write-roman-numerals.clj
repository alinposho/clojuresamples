(ns bookexamples.forclojure.write-roman-numerals)

;; Problem 104: This is the inverse of Problem 92, but much easier. Given an integer smaller than 4000, return the corresponding roman numeral in uppercase, adhering to the subtractive principle.
(defn write-roman-numerals [n]
  (let [m {1000 \M, 900 \D 500, \C 100, \L 50, \X 10, \V 5, \I 1}]
  (cond 
    (seq? xs) (m x)
    :else ((if (< (m x) (m (first xs))) - +) 
            (write-roman-numerals xs) 
            x))))

;; This does not work for lazy seqs and it's not complete
(reduce concat (map (fn [[a b]](if (< a b) [a :less b] [a b])) (partition 2 1 [1 6 7 4 3])))

(comment

(load-file "src/bookexamples/forclojure/write-roman-numerals.clj")
(refer 'bookexamples.forclojure.write-roman-numerals)

(= "I" (write-roman-numerals 1))
(= "XXX" (write-roman-numerals 30))
(= "IV" (write-roman-numerals 4))
(= "CXL" (write-roman-numerals 140))
(= "DCCCXXVII" (write-roman-numerals 827))
(= "MMMCMXCIX" (write-roman-numerals 3999))
(= "XLVIII" (write-roman-numerals 48))
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