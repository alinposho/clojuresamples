(ns the-joy-of-clojure.lazy-sequences)

(def very-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                    rest rest rest))

(def less-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                    next next next))