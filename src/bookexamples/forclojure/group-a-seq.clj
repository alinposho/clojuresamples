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
(not= true true false)
(not= true true)
(not= false false false)

)