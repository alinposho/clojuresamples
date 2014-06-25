(ns bookexamples.forclojure.transitive-clojure)

;; Transform set to a set of relations
(def rels (set (map #(apply hash-map %) #{[8 4] [9 3] [4 2] [27 9]})))
rels
(map #(filter not-empty (clojure.set/project rels [(key (first %))])) rels)
(map identity rels)
(filter not-empty (clojure.set/project rels [4]))
(key (first {4 2}))
;; Get relation values
(reduce #(conj %1 (val (first %2))) [] rels)
(reduce #(conj %1 (hash-map :a %2)) #{} [1 2 3 4])

(defn get-vals [rels]
	(reduce #(conj %1 (val (first %2))) [] rels))
(get-vals rels)

(defn get-keys [rels]
	(reduce #(conj %1 (key (first %2))) [] rels))
(get-keys rels)

(defn project-relations [rels ks]
	(set (filter not-empty (clojure.set/project rels ks))))

(project-relations rels [8 4])

(defn find-trans 
	"Finds the transitive relations starting from this key and returns them as a set"
	([k rels] 
		(let [vs (project-relations rels [k])] 
			  (find-trans k rels vs)))
	([k rels acc]
		(let [transitive-keys (get-vals acc)
		  	  vs (project-relations rels transitive-keys)
		  	  transitive-relations-values (get-vals vs)
		  	  transitive-relations (reduce #(conj %1 (hash-map k %2)) #{} transitive-relations-values)
		  	  new-transitive-relations (clojure.set/difference transitive-relations acc)]
		  (if (empty? new-transitive-relations) acc
			  (recur k rels (clojure.set/union acc new-transitive-relations))))))

(find-trans 8 rels)
(find-trans 8 rels #{{8 4}})

(defn vector-to-map [v] 
	(set (map #(apply hash-map %) v)))
(vector-to-map #{[8 4] [9 3] [4 2] [27 9]})

(defn map-to-vector [m]
	(reduce #(conj %1 (key %2) (val %2)) [] m))
(map-to-vector {1 2 4 5})

(defn transitive-clojure [relations]
	(let [rels (vector-to-map relations)
		  ks (get-keys rels)]
		(set (map map-to-vector (reduce clojure.set/union (map #(find-trans % rels) ks))))))

(transitive-clojure #{[8 4] [9 3] [4 2] [27 9]})


(comment

(load-file "src/bookexamples/forclojure/transitive_clojure.clj")
(refer 'bookexamples.forclojure.transitive-clojure)

(let [divides #{[8 4] [9 3] [4 2] [27 9]}]
  (= (transitive-clojure divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}))

(let [more-legs
      #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
  (= (transitive-clojure more-legs)
     #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
       ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}))

(let [progeny
      #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
  (= (transitive-clojure progeny)
     #{["father" "son"] ["father" "grandson"]
       ["uncle" "cousin"] ["son" "grandson"]}))


;; Some of the solutions on the web
(not= true true false)
(not= true true)
(not= false false false)

)