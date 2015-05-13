(ns bookexamples.forclojure.game-of-life-test
  (:require [clojure.test :refer :all]
            [bookexamples.forclojure.game-of-life :refer :all]))

(deftest conaway-game-of-life-test
  (testing "Tests of the function that outputs the next generation in the Conway's game of life"
    (is (= (next-generation ["      "
                                 " ##   "
                                 " ##   "
                                 "   ## "
                                 "   ## "
                                 "      "])
                            ["      "
                             " ##   "
                             " #    "
                             "    # "
                             "   ## "
                             "      "]))
    ))