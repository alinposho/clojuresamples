(ns bookexamples.forclojure.word-chains-test
  (:require [clojure.test :refer :all]
            [bookexamples.forclojure.word-chains :refer :all]))

(deftest word-chains-test
  (testing "Tests of the word chains problem"
    (is (= true (word-chains #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"})))
    (is (= false (word-chains #{"cot" "hot" "bat" "fat"})))
    (is (= false (word-chains #{"to" "top" "stop" "tops" "toss"})))
    (is (= true (word-chains #{"spout" "do" "pot" "pout" "spot" "dot"})))
    (is (= true (word-chains #{"share" "hares" "shares" "hare" "are"})))
    (is (= false (word-chains #{"share" "hares" "hare" "are"})))))