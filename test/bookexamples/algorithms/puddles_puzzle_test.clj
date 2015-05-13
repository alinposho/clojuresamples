(ns bookexamples.algorithms.puddles-puzzle-test
  (:require [clojure.test :refer :all]
            [bookexamples.algorithms.puddles-puzzle :refer :all]))

(deftest volume-puddle-puzzle-solution-test
  (testing "The two pass solution to the puddle puzzle problem."
    (is (= 0 (volume [1 2 3 4 7 7 6])))
    (is (= 0 (volume nil)))
    (is (= 0 (volume [1])))
    (is (= 0 (volume [1 2])))
    (is (= 10 (volume [2 5 1 2 3 4 7 7 6])))
    (is (= 20 (volume [2 7 1 2 5 3 4 7 7 6])))
    (is (= 20 (volume [2 8 1 2 5 3 4 7 7 6])))
    (is (= 5 (volume [2 4 3 7 7 2 6])))
    ))


(deftest vol-puddle-puzzle-solution-test
  (testing "The one pass solution to the puddle puzzle problem."
    (is (= 0 (vol [1 2 3 4 7 7 6])))
    (is (= 0 (vol nil)))
    (is (= 0 (vol [1])))
    (is (= 0 (vol [1 2])))
    (is (= 10 (vol [2 5 1 2 3 4 7 7 6])))
    (is (= 20 (vol [2 7 1 2 5 3 4 7 7 6])))
    (is (= 20 (vol [2 8 1 2 5 3 4 7 7 6])))
    (is (= 5 (vol [2 4 3 7 7 2 6])))
    ))
