(ns bookexamples.forclojure.tricky-card-game-test
  (:require [clojure.test :refer :all]
            [bookexamples.forclojure.tricky-card-game :refer :all]))

(deftest tricky-card-game-test
  (testing "Tests of the tricky card game problem"
    (is (let [notrump (tricky-card-game nil)]
          (and (= {:suit :club :rank 9}  (notrump [{:suit :club :rank 4}
                                                   {:suit :club :rank 9}]))
               (= {:suit :spade :rank 2} (notrump [{:suit :spade :rank 2}
                                                   {:suit :club :rank 10}])))))
    (is (= {:suit :club :rank 10} ((tricky-card-game :club) [{:suit :spade :rank 2}
                                               {:suit :club :rank 10}])))
    (is (= {:suit :heart :rank 8}
           ((tricky-card-game :heart) [{:suit :heart :rank 6} {:suit :heart :rank 8}
                         {:suit :diamond :rank 10} {:suit :heart :rank 4}])))))

