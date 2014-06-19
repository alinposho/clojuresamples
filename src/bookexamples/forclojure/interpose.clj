(ns bookexamples.forclojure.interpose)

(defn inter [v xs]
  (drop-last (mapcat #(list %1 v) xs)))

(inter :a '(1 2 3))