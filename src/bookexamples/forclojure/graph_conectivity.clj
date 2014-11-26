(ns bookexamples.forclojure.graph-conectivity)

(defn connected?
  ([graph] (boolean (connected? (set (first graph)) (set (rest graph)))))
  ([connected-subgraph graph]
   (letfn [(conn-subgraph [[a b :as edge]]
            (and
              (boolean (or (connected-subgraph a) (connected-subgraph b)))
              (connected? (into connected-subgraph edge) (disj graph edge))))]
     (or
      (empty? graph)
      (some conn-subgraph graph)))))

(comment

(load-file "src/bookexamples/forclojure/infix.clj")
(refer 'bookexamples.forclojure.infix)

(= true (connected? #{[:a :a]}))
(= true (connected? #{[:a :b]}))
(= false (connected? #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4]}))
(= true (connected? #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4] [3 4]}))
(= false (connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e]}))
(= true (connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e] [:x :a]}))

(= false (connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e] [:x :z]}))



;; Some of the solutions on the web
(fn [x] (letfn [(adjlist [e] (let [
                                   ee (concat e (map reverse e))
                                   ve (group-by first ee)]
                               (into {} (for [[k v] ve] [k (map second v)]))))
                (connected? [g] (letfn [
                                        (add-vertex [s v] (if (s v) s
                                                                    (reduce add-vertex (conj s v) (g v))))]
                                  (= (count (add-vertex #{} (ffirst g))) (count g))))]
          (connected? (adjlist x))))
)
