(ns bookexamples.forclojure.balancing-brackets-test
  (:require [clojure.test :refer :all]
            [bookexamples.forclojure.balancing-brackets :refer :all]))

(deftest balancing-brackets-test
  (testing "Tests of the balancing brackets problem"
    (is (balanced-brackets? "This string has no brackets."))
    (is (balanced-brackets? "class Test {
                public static void main(String[] args) {
                  System.out.println(\"Hello world.\");
                }
              }"))
    (is (not (balanced-brackets? "(start, end]")))
    (is (not (balanced-brackets? "())")))
    (is (not (balanced-brackets? "[ { ] } ")))
    (is (balanced-brackets? "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))"))
    (is (not (balanced-brackets? "([]([(()){()}(()(()))(([[]]({}([)))())]((((()()))))))")))
    (is (not (balanced-brackets? "[")))))
