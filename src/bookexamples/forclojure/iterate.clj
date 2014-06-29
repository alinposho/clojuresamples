(ns bookexamples.forclojure.my-iterate)

(defn my-iterate [f x]
	(cons x (lazy-seq (my-iterate f (f x)))))

(comment

(load-file "src/bookexamples/forclojure/iterate.clj")
(refer 'bookexamples.forclojure.my-iterate)

(take 10 (my-iterate inc 1))
(my-iterate true true)
(my-iterate false false)


;; Some of the solutions on the web
(take 10 ((fn [f x] (reductions #(%2 %1) x (repeat f))) inc 1))

)