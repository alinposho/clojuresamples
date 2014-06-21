(ns bookexamples.forclojure.comp)

(defn my-comp [& fs]
	(reduce (fn [f1 f2] (fn [params] (f2 (f1 params)))) (reverse fs)))


(comment

(load-file "src/bookexamples/forclojure/comp.clj")
(refer 'bookexamples.forclojure.comp)

((my-comp first rest reverse) [1 2 3 4])
(my-comp [1 2 3 4 5])

;; Some of the solutions on the web
(#(map (vec %) (range (dec (count %)) -1 -1)) '(1 2 3 4))
(#(into () %) [1 2 3 4])

)