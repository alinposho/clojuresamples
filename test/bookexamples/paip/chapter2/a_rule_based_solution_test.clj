(ns bookexamples.paip.chapter2.a-rule-based-solution-test
  (:require [clojure.test :refer :all]
            [bookexamples.paip.chapter2.a-rule-based-solution :refer :all]))


(deftest rule-rhs-test
  (testing "Test rule-rhs"
    (is (= (rule-rhs '(sentence -> (noun-phrase verb-phrase))) '((noun-phrase verb-phrase))))
    (is (= (rule-rhs '(noun-phrase -> (Article Noun))) '((Article Noun))))
    (is (= (rule-rhs '(verb-phrase -> (Verb noun-phrase))) '((Verb noun-phrase))))
    (is (= (rule-rhs '(Article -> the a)) '(the a)))
    (is (= (rule-rhs '(Noun -> man ball woman table)) '(man ball woman table)))
    (is (= (rule-rhs '(Verb -> hit took s a w liked)) '(hit took s a w liked)))))