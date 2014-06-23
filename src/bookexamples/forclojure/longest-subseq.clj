(ns bookexamples.forclojure.longest-subseq)

(defn longest-subseq [coll]
	(reverse (last (sort-by count (filter #(> (count %) 1) 
		(reduce (fn [[x & xs :as s] e] 
					(if (= (first x) (dec e)) (cons (cons e x) xs)
						(cons (list e) s))) 
				[()] 
				coll))))))

(longest-subseq [1 0 1 2 3 0 4 5])
(longest-subseq [5 6 1 3 2 7])
(longest-subseq [2 3 3 4 5])
(longest-subseq	[7 6 5 4])

(defn partition-by-increasing-subseq [y & ys]
	(reduce (fn [[x & xs :as s] e] 
					(if (= (first x) (dec e)) (cons (cons e x) xs)
						(cons (list e) s))) 
				[(list y)] 
				ys))

(partition-by-increasing-subseq [1 0 1 2 3 0 4 5])


(comment

(load-file "src/bookexamples/forclojure/longest-subseq.clj")
(refer 'bookexamples.forclojure.longest-subseq)

(longest-subseq 2 [1 2 3 4 5])

;; Some of the solutions on the web
(def f1 
	(fn [coll]
	  (let [seqs (reverse (map #(concat (first %) (map (fn [i] (last i)) (rest %)))
	                        (filter #(every? (fn [ele] (< (first ele) (last ele))) %)
	                          (partition-by
	                            #(< (first %) (last %))
	                            (partition 2 1 coll)))))]
	    (if (empty? seqs)
	      []
	      (apply (partial max-key count) seqs)))))
(def f2 (comp #(list (first %) (apply concat (next %))) partition-all))
(f1 2 [1 2 3 4 5])
(f2 2 [1 2 3 4 5])


)