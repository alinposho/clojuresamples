(ns bookexamples.forclojure.graph-tour-test
  (:require [clojure.test :refer :all]
            [bookexamples.forclojure.graph-tour :refer :all]))

(deftest graph-tour-test
  (testing "Tests of the graph tour problem"
    (is (= true (graph-tour? [[:a :b]])))
    (is (= false (graph-tour? [[:a :a] [:b :b]])))
    (is (= false (graph-tour? [[:a :b] [:a :b] [:a :c] [:c :a] [:a :d] [:b :d] [:c :d]])))
    (is (= true (graph-tour? [[1 2] [2 3] [3 4] [4 1]])))
    (is (= true (graph-tour? [[:a :b] [:a :c] [:c :b] [:a :e]
                              [:b :e] [:a :d] [:b :d] [:c :e]
                              [:d :e] [:c :f] [:d :f]])))
    (is (= false (graph-tour? [[1 2] [2 3] [2 4] [2 5]])))))