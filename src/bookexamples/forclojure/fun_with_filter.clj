(ns bookexamples.forclojure.fun-with-filter)

(= [6 7] (filter #(> % 5) '(3 4 5 6 7)))