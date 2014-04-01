(ns the-joy-of-clojure.lazy-sequences)

(def very-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                    rest rest rest))

(def less-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                    next next next))


(defn positive-numbers 
  ([] (positive-numbers 1))
  ([n] (cons n (lazy-seq (positive-numbers (inc n))))))

(take 10 (positive-numbers))