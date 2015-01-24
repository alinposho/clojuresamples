(ns bookexamples.functional-thinking.chapter5.bendable-language-test
  (:require [clojure.test :refer :all]
            [bookexamples.functional-thinking.chapter5.bendable-language :refer :all]))

(deftest numeric-letter-grades
  (dorun (map #(is (= "A" (letter-grade %))) (range 90 100)))
  (dorun (map #(is (= "B" (letter-grade %))) (range 80 89)))
  (dorun (map #(is (= "C" (letter-grade %))) (range 70 79)))
  (dorun (map #(is (= "D" (letter-grade %))) (range 60 69)))
  (dorun (map #(is (= "F" (letter-grade %))) (range 0 59))))

