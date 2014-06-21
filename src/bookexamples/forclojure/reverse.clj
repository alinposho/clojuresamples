(ns bookexamples.forclojure.reverse)

(defn my-reverse [col]
	(reduce conj nil col))


(comment

(load-file "src/bookexamples/forclojure/reverse.clj")
(refer 'bookexamples.forclojure.reverse)

(my-reverse '(1 2 3 4 5))
(my-reverse [1 2 3 4 5])

;; Some of the solutions on the web
(#(map (vec %) (range (dec (count %)) -1 -1)) '(1 2 3 4))
(#(into () %) [1 2 3 4])

)