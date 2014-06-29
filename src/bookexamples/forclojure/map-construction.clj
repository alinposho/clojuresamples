(ns bookexamples.forclojure.map-construction)

(defn map-construction [xs ys]
	(apply assoc {} (interleave xs ys)))

;; First working solution 
; (defn map-construction [xs ys]
; 	(reduce #(apply (partial assoc %1) %2) {} (map #(vector %1 %2) xs ys)))

(comment

(load-file "src/bookexamples/forclojure/map-construction.clj")
(refer 'bookexamples.forclojure.map-construction)

(= (map-construction [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})
(= (map-construction [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})
(= (map-construction [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"})

;; Some of the solutions on the web
(#(into {} (map vector % %2)) [:a :b :c] [1 2 3])
((comp (partial apply sorted-map) interleave) [:a :b :c] [1 2 3])

)