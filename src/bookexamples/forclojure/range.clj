(ns bookexamples.forclojure.range)

(defn my-range [x y]
	(take-while #(< % y) (iterate inc x)))

(comment

(load-file "src/bookexamples/forclojure/range.clj")
(refer 'bookexamples.forclojure.range)

(my-range 1 4)
(my-range -2 2)

;; Some of the solutions on the web
((fn [x y] (take (- y x) (iterate inc x))) -4 -2)

)