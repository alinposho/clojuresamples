(ns bookexamples.forclojure.comp)

(defn my-comp [& fs]
	(reduce (fn [f1 f2] (fn [& params] (f2 (apply f1 params)))) (reverse fs)))


(comment

(load-file "src/bookexamples/forclojure/comp.clj")
(refer 'bookexamples.forclojure.comp)

((my-comp first rest reverse) [1 2 3 4])
((my-comp zero? #(mod % 8) +) 3 5 7 9)

;; Some of the solutions on the web
(((fn [& x] (reduce (fn [f g] (fn [& a] (f (apply g a)))) x)) zero? #(mod % 8) +) 3 5 7 9 )

(fn comb [& funcs]
  (fn [& args]
    (first
      (reduce #(vector (apply %2 %1)) args (reverse funcs )))))

)