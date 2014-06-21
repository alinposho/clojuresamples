(ns bookexamples.forclojure.nth)

(defn my-nth [coll n]
	((vec coll) n))

;; Interestingly enough the following will not work
;(into {} (map #(list %1 value) m-keys)))

(comment


(load-file "src/bookexamples/forclojure/nth.clj")
(refer 'bookexamples.forclojure.nth)

(my-nth '(1 2 3 4 5) 4)

(#((vec %1) %2) [:a 4 5 :b] 3)

;; Some of the solutions on the web
(#(first (drop %2 %)) [:a 4 5 :b] 3)
((fn [coll n] (first (nthnext coll n))) [:a 4 5 :b] 3)
)