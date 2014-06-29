(ns bookexamples.forclojure.clojures)

(defn pow-n [n]
	#(apply * (take n (repeat %1))))

(comment

(load-file "src/bookexamples/forclojure/clojures.clj")
(refer 'bookexamples.forclojure.clojures)

((pow-n 10) 2)
(clojures true true)
(clojures false false)


;; Some of the solutions on the web
((partial (fn [n x] (apply * (repeat n x))) 10) 2)
(fn [n] #(Math/pow % n))

)