(ns bookexamples.forclojure.seq-reductions)

(defn seq-reductions 
	([f [x & xs]] (seq-reductions f (f x) xs))
	([f acc [x & xs]] 
		(if (nil? x) 
			(list acc)
			(cons acc (lazy-seq (seq-reductions f (f acc x) xs))))))


(comment

(load-file "src/bookexamples/forclojure/seq-reductions.clj")
(refer 'bookexamples.forclojure.seq-reductions)

(take 5 (seq-reductions + (range)))
(take 5 (seq-reductions conj [1] [2 3 4]))
(reductions conj [1] [2 3 4])
(= (take 5 (seq-reductions + (range))) [0 1 3 6 10])
(= (seq-reductions conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])
(= (last (seq-reductions * 2 [3 4 5])) (reduce * 2 [3 4 5]) 12 0)


;; Some of the solutions on the web
(fn my-reduce
  ([op input] (my-reduce op (first input) (rest input)))
  ([op result input]
	  (lazy-seq
	    (if (empty? input) (list result)
	      (cons result
	            (my-reduce op
	                 (op result (first input))
	                 (rest input)))))))
)