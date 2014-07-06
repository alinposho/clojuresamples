(ns bookexamples.forclojure.partition-seq)

(defn partition-seq [n xs] 
	(letfn [(repetitive-split [n xs]
				(cons (take n xs) (lazy-seq (repetitive-split n (drop n xs)))))]
	(take-while #(= n (count %)) (repetitive-split n xs))))

(defn repetitive-split [n xs]
	(cons (take n xs) (lazy-seq (repetitive-split n (drop n xs)))))

(take 5 (repetitive-split 3 (range 10)))

(comment

(load-file "src/bookexamples/forclojure/partition-seq.clj")
(refer 'bookexamples.forclojure.partition-seq)

(partition-seq 3 (range 10))



;; Some of the solutions on the web
(fn f [n v]
  (if (>= (count v) n)
    (cons (take n v) (f n (drop n v)))))

)