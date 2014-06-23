(ns bookexamples.forclojure.half-truth)

(defn half-truth [x & xs]
	(not-every? #(= % x) xs))

(comment

(load-file "src/bookexamples/forclojure/half-truth.clj")
(refer 'bookexamples.forclojure.half-truth)

(half-truth true true false)
(half-truth true true)
(half-truth false false)


;; Some of the solutions on the web
(not= true true false)
(not= true true)
(not= false false false)

)