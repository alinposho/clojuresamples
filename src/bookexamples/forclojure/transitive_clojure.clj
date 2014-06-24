(ns bookexamples.forclojure.transitive-clojure)

(defn transitive-clojure [x & xs]
	(not-every? #(= % x) xs))

;; Transform set to a set of relations
(def rels (set (map #(apply hash-map %) #{[8 4] [9 3] [4 2] [27 9]})))
rels
(map #(filter not-empty (clojure.set/project rels [(key (first %))])) rels)
(map identity rels)
(filter not-empty (clojure.set/project rels [4]))
(key (first {4 2}))


(comment

(load-file "src/bookexamples/forclojure/transitive_clojure.clj")
(refer 'bookexamples.forclojure.transitive-clojure)

(transitive-clojure true true false)
(transitive-clojure true true)
(transitive-clojure false false)


;; Some of the solutions on the web
(not= true true false)
(not= true true)
(not= false false false)

)