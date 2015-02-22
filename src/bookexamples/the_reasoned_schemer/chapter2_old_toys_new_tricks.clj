(ns bookexamples.the-reasoned-schemer.chapter2-old-toys-new-tricks
  (:require [clojure.core.logic :refer :all]))

;;; The following are the examples from chapter 2 of "The Reasoned Schemer"(TRS) book
;; Please note that there are some differences between core.logic and the logic library used in TRS described
;; here: https://github.com/clojure/core.logic/wiki/Differences-from-The-Reasoned-Schemer
;; The main reason for these difference is because the # character in Clojure is the dispatch macro:
;; "The dispatch macro causes the reader to use a reader macro from another table, indexed by the character following #".
;; See: http://clojure.org/reader for more details


(let [x (fn [a] a)
      y 'c]
  (x y))


(run* [r]
  (fresh [x y]
    (== (list x y) r)))

(run* [r]
  (fresh [x y]
    (== '(x y) r))) ;; Interesting that the quote will prevent the list from being evaluated unifying r with (list 'x 'y)


(run* [r]
  (fresh [v w]
    (== (let [x v
              y w]
          (list x y))
      r)))


(run* [r]
  (firsto '(a b c d) r))


;(run* [r]
;  (fresh [x y]
;    (firsto (list 'grep 'raisin 'pair) x)
;    (firsto (list (list 'a) (list 'b) (list 'c)) y)
;    (== (cons x y) r)))

