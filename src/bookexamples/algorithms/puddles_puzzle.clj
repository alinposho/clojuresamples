(ns bookexamples.algorithms.puddles-puzzle)

;;; The specification of the problem: https://pavelfatin.com/twitter-puddles-and-foldlr/
;; We are given a list of numbers, representing walls of different height. The goal is to determine how much rain water
;; can be accumulated between the walls. For example, for [2,5,1,2,3,4,7,7,6]
;; the amount of accumulated water is 10.

(defn volume [xs]
  (if-not (seq? (seq xs))
    0
    (reduce + 0
            (map (fn [lm xs rm] (- (min lm rm) xs))
                 (reductions max xs)
                 xs
                 (reverse (reductions max (reverse xs)))))))

(comment
  (def xs [2 5 1 2 3 4 7 7 6])
  (def lm (reductions max xs))
  (def rm (reverse (reductions max (reverse xs))))

  (map (fn [lm xs rm] (- (min lm rm) xs)) lm xs rm)

  (volume xs)
  )
