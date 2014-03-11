(ns bookexamples.forclojure.fun-with-map)

(= '(6 7 8) (map #(+ % 5) '(1 2 3)))