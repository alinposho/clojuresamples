(ns bookexamples.algorithms.puddles-puzzle-test
  (:require [clojure.test :refer :all]
            [bookexamples.algorithms.puddles-puzzle :refer :all]))

(deftest puddle-puzzle-test
  (testing "The two pass solution to the puddle puzzle problem."
    (is (= 10 (volume [2 5 1 2 3 4 7 7 6])))
    (is (= 0 (volume [1 2 3 4 7 7 6])))
    (is (= 0 (volume nil)))
    (is (= 0 (volume [1])))
    (is (= 0 (volume [1 2])))
    ))
