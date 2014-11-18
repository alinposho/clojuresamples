(ns bookexamples.forclojure.graph-conectivity-test
  (:require [clojure.test :refer :all]
            [bookexamples.forclojure.graph-conectivity :refer :all]))

(deftest balanced-brackets-test
  (testing "Tests of the graph connectivity problem"
    (is (= true (connected? #{[:a :a]})))
    (is (= true (connected? #{[:a :b]})))
    (is (= false (connected? #{[1 2] [2 3] [3 1]
                   [4 5] [5 6] [6 4]})))
    (is (= true (connected? #{[1 2] [2 3] [3 1]
                  [4 5] [5 6] [6 4] [3 4]})))
    (is (= false (connected? #{[:a :b] [:b :c] [:c :d]
                   [:x :y] [:d :a] [:b :e]})))
    (is (= true (connected? #{[:a :b] [:b :c] [:c :d]
                  [:x :y] [:d :a] [:b :e] [:x :a]})))))


