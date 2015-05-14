(ns bookexamples.algorithms.puddles-puzzle
  (:require [bookexamples.util.functions :refer :all]))

;;; The specification of the problem: https://pavelfatin.com/twitter-puddles-and-foldlr/
;; We are given a list of numbers, representing walls of different height. The goal is to determine how much rain water
;; can be accumulated between the walls. For example, for [2,5,1,2,3,4,7,7,6]
;; the amount of accumulated water is 10.

;; The following solution, although functional in nature, not quite as efficient as it could be
;; since it passes through the collections three times. The goal is to traverse the sequence only once
(defn volume [xs]
  (if-not (seq? (seq xs))
    0
    (reduce + 0
            (map (fn [lm xs rm] (- (min lm rm) xs))
                 (reductions max xs)
                 xs
                 (reverse (reductions max (reverse xs))))))) ;; we could improve this by defining a one pass reductions right

;; Solution that traverses the collection only once.
(def vol
  (letfn [(f [l xs] ;; This function returns a vector of the puddle volume and the max wall comming(folding) from the right
             (if (seq xs)
               (let [x (first xs)
                     [s r] (f (max l x) (rest xs))]
                 [(+ s (max 0 (- (min l r) x))) (max x r)])
               [0 0]))]
    (comp first (partial f 0))))


(comment
  (def xs [2 5 1 2 3 4 7 7 6])
  (def lm (reductions max xs))
  (def rm (reverse (reductions max (reverse xs))))

  (map (fn [lm xs rm] (- (min lm rm) xs)) lm xs rm)

  (volume xs)

  (vol xs)
  )
