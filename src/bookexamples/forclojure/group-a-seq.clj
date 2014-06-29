(ns bookexamples.forclojure.group-a-seq)

(defn group-a-seq [f xs]
	(reduce (fn [m e] (assoc m (f e) (conj (m (f e) []) e))) {} xs))

(comment

(load-file "src/bookexamples/forclojure/group-a-seq.clj")
(refer 'bookexamples.forclojure.group-a-seq)

(group-a-seq #(> % 5) [1 3 6 8])
(= (group-a-seq #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
(= (group-a-seq #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})
(= (group-a-seq count [[1] [1 2] [3] [1 2 3] [2 3]])
   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})

;; Some of the solutions on the web
(def f1 (comp #(zipmap (map ffirst %) (map (partial map peek) %)) (partial partition-by first) sort (fn [f x] (map-indexed #(vector (f %2) %1 %2) x)))))
(f1 #(> % 5) [1 3 6 8])
(def f2 
	(fn my-group-by [f coll] 
		  (persistent! 
		    (reduce (fn [ret x] 
		      (let [k (f x)] 
		(assoc! ret k 
		  (conj (get ret k []) x)))) 
		(transient {}) coll))))



)