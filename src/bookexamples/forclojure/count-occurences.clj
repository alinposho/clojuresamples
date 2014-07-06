(ns bookexamples.forclojure.count-occurences)

(defn occurences [xs] 
	(reduce (fn [m elem] (assoc m elem (inc (get m elem 0)))) {} xs))


(comment

(load-file "src/bookexamples/forclojure/count-occurences.clj")
(refer 'bookexamples.forclojure.count-occurences)

(= (occurences [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})
(= (occurences [:b :a :b :a :b]) {:a 2, :b 3})
(= (occurences '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})


;; Some of the solutions on the web
#(into {}
  (map (fn [[k v]] [k (count v)]) (group-by identity %)))

(comp #(zipmap (keys %) (map count (vals %))) (partial group-by identity))

)