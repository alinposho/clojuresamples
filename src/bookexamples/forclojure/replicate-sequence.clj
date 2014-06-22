(ns bookexamples.forclojure.replicate-sequence)

(defn replicate-sequence [xs n]
	(mapcat #(take n (repeat %)) xs))

(comment

(load-file "src/bookexamples/forclojure/replicate-sequence.clj")
(refer 'bookexamples.forclojure.replicate-sequence)

(replicate-sequence [1 2 3 4] 3)
(replicate-sequence [[1 2] [1 2] [3 4] [1 2]])

;; Some of the solutions on the web
(fn [coll n] 
  (apply concat (map #(repeat n %) coll)))

(fn [x t] (mapcat (partial repeat t) x))

)