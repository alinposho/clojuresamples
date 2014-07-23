(ns bookexamples.forclojure.lazy-searching)

;; Problem 108: Given any number of sequences, each sorted from smallest to largest, find the smallest single number which appears in all of the sequences. The sequences may be infinite, so be careful to search lazily.
(defn search [[x & xs] & sqs]
    (if (empty? sqs) 
      x
      (letfn [(sq-contains? [sq x]
                (if (seq? sq) 
                    (if (> x (first sq)) 
                        (sq-contains? (rest sq) x)
                        (= x (first sq)))))]
        (if (every? #(sq-contains? (seq %) x) sqs) 
            x
            (apply search (cons xs sqs))))))

(defn sq-contains? [sq x]
  (if (seq? sq) 
      (if (> x (first sq)) 
          (sq-contains? (rest sq) x)
          (= x (first sq)))))

(comment

(load-file "src/bookexamples/forclojure/lazy-searching.clj")
(refer 'bookexamples.forclojure.lazy-searching)

(sq-contains? (range) 100)
(every? #(sq-contains? (seq %) 4) [[0.5 3/2 4 19]])

(= 3 (search [3 4 5]))
(= 4 (search [1 2 3 4 5 6 7] [0.5 3/2 4 19]))
(= 7 (search (range) (range 0 100 7/6) [2 3 5 7 11 13]))
(= 64 (search (map #(* % % %) (range)) ;; perfect cubes
          (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
          (iterate inc 20))) ;; at least as large as 20

;; Some of the solutions on the web
;; I don't want to know what this does
(fn i                                                                                                                         
  [& x]                                                                                                                       
    (let [f first                                                                                                             
          a apply                                                                                                     
          [[b & e] & c] (sort-by f x)]                                                                                                         
      (if (a = (map f x))                                                                                                             
       b                                                                                                                   
       (a i (conj c e)))))

(fn lazy-search [& colls]
  (let [first-eles (map first colls)]
    (if (every? #(= (first first-eles) %) first-eles)
      (first first-eles)
      (let [sorted-colls (sort-by first colls)]
        (apply lazy-search (cons (rest (first sorted-colls)) (rest sorted-colls)))))))

(letfn [
    (match? [x s] (or (= x (first s)) (and (> x (first s)) (recur x (rest s)))))
    (first-common [s1 & sn] (first (drop-while #(not-every? (partial match? %) sn) s1)))]
    first-common)


)