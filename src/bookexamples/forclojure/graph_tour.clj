(ns bookexamples.forclojure.graph-tour)

(defn graph-tour? [graph]
  true)


(comment

  (load-file "src/bookexamples/forclojure/graph_tour.clj")
  (refer 'bookexamples.forclojure.graph-tour)

  (= true (graph-tour? [[:a :b]]))
  (= false (graph-tour? [[:a :a] [:b :b]]))
  (= false (graph-tour? [[:a :b] [:a :b] [:a :c] [:c :a]
                [:a :d] [:b :d] [:c :d]]))
  (= true (graph-tour? [[1 2] [2 3] [3 4] [4 1]]))
  (= true (graph-tour? [[:a :b] [:a :c] [:c :b] [:a :e]
               [:b :e] [:a :d] [:b :d] [:c :e]
               [:d :e] [:c :f] [:d :f]]))
  (= false (graph-tour? [[1 2] [2 3] [2 4] [2 5]]))

  ;; Some of the solutions on the web

  )
