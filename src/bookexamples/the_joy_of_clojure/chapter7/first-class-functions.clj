(ns the-joy-of-clojure.first-class-functions)


(defn fnth [n]
  (apply comp
       	 (cons first
			   (take (dec n) (repeat rest)))))

((fnth 5) [1 2 3 4 5])