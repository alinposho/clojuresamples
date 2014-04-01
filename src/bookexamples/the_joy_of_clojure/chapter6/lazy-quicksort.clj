(ns the-joy-of-clojure.lazy-quicksort)

(defn sort-parts
"Lazy, tail-recursive, incremental quicksort. Works against
and creates partitions based on the pivot, defined as 'work'."
  [work]
  (loop [[part & parts] work]
  	(if-let [[pivot & xs] (seq part)]
  		(let [smaller? #(< % pivot)]
  			(recur (list* (filter smaller? xs)
					   pivot 
					   (remove smaller? xs)
					   parts)))
  		;; else
  		(when-let [[x & parts] parts]
  			(cons x (sort-parts parts))))))

(defn qsort [xs]
	(sort-parts (list xs)))


; (qsort [4 2 9 17 3 1])

(defn random-seq 
  	"Returns a lazy sequence of random integer between 0 (inclusive) and n (exclusive)"
  [n]
	(repeatedly #(rand-int n)))

;(take 10 (random-seq 30))
;(qsort (take 10 (random-seq 100)))