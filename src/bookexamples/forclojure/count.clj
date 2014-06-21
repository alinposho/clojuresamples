(ns bookexamples.forclojure.count)

(defn my-count [coll]
	(reduce (fn [c e] (inc c)) 0 coll))


(comment

(load-file "src/bookexamples/forclojure/count.clj")
(refer 'bookexamples.forclojure.count)

(my-count [1 2 3 4 5])
(my-count "abcdef")

;; Solution no 2
(#(reduce + (map (fn [_] 1) %1)) "abcdef")

;; Some of the solutions on the web

((comp (partial apply +) (partial map #(do % 1))) "Hello World!")
(#(reduce + (map (fn [x] 1) %)) #{1 2 3 4 5})

)