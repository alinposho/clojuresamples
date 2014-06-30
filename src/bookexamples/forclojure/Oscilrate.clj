(ns bookexamples.forclojure.oscilrate)

(defn oscilrate [x & fs]
	(reductions #(%2 %1) x (cycle fs)))

(comment

(load-file "src/bookexamples/forclojure/oscilrate.clj")
(refer 'bookexamples.forclojure.oscilrate)

(= (take 3 (oscilrate 3.14 int double)) [3.14 3 3.0])
(= (take 5 (oscilrate 3 #(- % 3) #(+ 5 %))) [3 0 5 2 7])
(= (take 12 (oscilrate 0 inc dec inc dec inc)) [0 1 0 1 0 1 2 1 2 1 2 3])


;; Some of the solutions on the web

)