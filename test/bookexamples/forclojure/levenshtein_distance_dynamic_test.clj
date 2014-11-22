(ns bookexamples.forclojure.levenshtein-distance-dynamic-test
  (:require [clojure.test :refer :all]
            [bookexamples.forclojure.levenshtein-distance-dynamic :refer :all]))

(deftest levenshtein-distance-dynamic-test
  (testing "Tests of the Levenshtein distance problem"
    (is (= (levenshtein "kitten" "kitten") 0))
    (is (= (levenshtein "kitten" nil) 6))
    (is (= (levenshtein "" "kitten") 6))
    (is (= (levenshtein "kitten" "sitting") 3))
    (is (= (levenshtein "closure" "clojure") (levenshtein "clojure" "closure") 1))
    (is (= (levenshtein "xyx" "xyyyx") 2))
    (is (= (levenshtein "" "123456") 6))
    (is (= (levenshtein "Clojure" "Clojure") (levenshtein "" "") (levenshtein [] []) 0))
    (is (= (levenshtein [1 2 3 4] [0 2 3 4 5]) 2))
    (is (= (levenshtein '(:a :b :c :d) '(:a :d)) 2))
    (is (= (levenshtein "ttttattttctg" "tcaaccctaccat") 10))
    (is (= (levenshtein "gaattctaatctc" "caaacaaaaaattt") 9))))