(ns bookexamples.forclojure.reverse-interleave)

(defn reverse-interleave [xs n]
	(let [ys (partition-all n xs)]
		(partition-all (count ys) (apply interleave ys))))

(comment

(load-file "src/bookexamples/forclojure/reverse-interleave.clj")
(refer 'bookexamples.forclojure.reverse-interleave)

(reverse-interleave (range 9) 3)
(reverse-interleave (range 10) 5)

;; Some of the solutions on the web
(def f1 
	(fn [coll n]
	    (apply map list (partition n coll))))
(f1 (range 9) 3)
(f1 (range 10) 5)


)