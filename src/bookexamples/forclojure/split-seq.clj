(ns bookexamples.forclojure.split-seq)

(defn split-seq [n xs]
	(let [xp (partition-all n xs)]
		(list (first xp) (apply concat (rest xp)))))

(comment

(load-file "src/bookexamples/forclojure/split-seq.clj")
(refer 'bookexamples.forclojure.split-seq)

(split-seq 2 [1 2 3 4 5])

;; Some of the solutions on the web
(def f1 
	(fn [n coll]
	  [(take n coll) (drop n coll)]))
(def f2 (comp #(list (first %) (apply concat (next %))) partition-all))
(f1 2 [1 2 3 4 5])
(f2 2 [1 2 3 4 5])


)