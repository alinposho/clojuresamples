(ns bookexamples.forclojure.transitive-clojure)

(defn transitive-clojure [relations]
	(letfn [(vector-to-map [v] 
				(set (map #(apply hash-map %) v)))
			(map-to-vector [m]
				(reduce #(conj %1 (key %2) (val %2)) [] m))
			(project-relations [rels ks]
				(set (filter not-empty (clojure.set/project rels ks))))
			(get-keys [rels]
				(reduce #(conj %1 (key (first %2))) [] rels))
			(get-vals [rels]
				(reduce #(conj %1 (val (first %2))) [] rels))
			(find-trans 
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
						  (recur k rels (clojure.set/union acc new-transitive-relations))))))]
		(let [rels (vector-to-map relations)
			  ks (get-keys rels)]
			(set (map map-to-vector (reduce clojure.set/union (map #(find-trans % rels) ks)))))))



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
(fn [s]
  (letfn [(step [ele s]
            (let [n (filter #(= (second ele) (first %)) s)]
              (if (seq n)
                (let [t (map #(vec [(first ele) (second %)]) n)]
                  (cons ele (mapcat #(step % s) t)))
                [ele])))]
    (set (mapcat #(step % s) s))))

(letfn [
    (update [e x] (let [
        in  (for [ei e :when (= x (second ei))] (first  ei))
        out (for [ei e :when (= x (first  ei))] (second ei))]
        (into e (for [v1 in v2 out] [v1 v2]))))
    (trans [e] (reduce update e (distinct (flatten (vec e)))))]
    trans)

)