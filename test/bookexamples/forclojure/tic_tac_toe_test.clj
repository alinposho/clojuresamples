(ns bookexamples.forclojure.tic-tac-toe-test
  (:require [clojure.test :refer :all]
            [bookexamples.forclojure.tic-tac-toe :refer :all]))

(deftest tic-tac-toe-test
  (testing "Tests the tic tac toe board problem from 4Clojure"
    (is (= nil (analyze-ttt [[:e :e :e]
                [:e :e :e]
                [:e :e :e]])))
    (is (= :x (analyze-ttt [[:x :e :o]
               [:x :e :e]
               [:x :e :o]])))
    (is (= :o (analyze-ttt [[:e :x :e]
               [:o :o :o]
               [:x :e :x]])))
    (is (= nil (analyze-ttt [[:x :e :o]
                [:x :x :e]
                [:o :x :o]])))
    (is (= :x (analyze-ttt [[:x :e :e]
               [:o :x :e]
               [:o :e :x]])))
    (is (= :o (analyze-ttt [[:x :e :o]
               [:x :o :e]
               [:o :e :x]])))
    (is (= nil (analyze-ttt [[:x :o :x]
                [:x :o :x]
                [:o :x :o]])))))

