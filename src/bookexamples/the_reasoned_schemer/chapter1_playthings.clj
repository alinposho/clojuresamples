(ns bookexamples.the-reasoned-schemer.chapter1-playthings
  (:require [clojure.core.logic :refer :all]))

;;; The following are the examples from chapter 1 of "The Reasoned Schemer"(TRS) book
;; Please note that there are some differences between core.logic and the logic library used in TRS described
;; here: https://github.com/clojure/core.logic/wiki/Differences-from-The-Reasoned-Schemer
;; The main reason for these difference is because the # character in Clojure is the dispatch macro: 
;; "The dispatch macro causes the reader to use a reader macro from another table, indexed by the character following #".
;; See: http://clojure.org/reader for more details


(run* [q]
      u#)

(run* [q]
      (== true q))

;; () because the first goa s# fails
(run* [q]
      u#
      (== true q))

(run* [q]
      s#
      (== true q))

(run* [q]
      s#
      (== 'corn q))

;; Notice that the logic expression resturns a list and that the q variable is bound to the 'corn expression
;; inside the logic block  
(let [a (run* [q]
              s#
              (== 'corn q))]
  (first a))

(run* [q]
      u#
      (== 'corn q))

(let [x false]
  (== false x))

(run* [x]
      (let [x false]
        (== false x)))

(run* [x]
      (let [x false]
        (== true x)))

(run* [q]
      (fresh [x]
             (== false x)
             (== true q)))

;; The order of the arguments to == does not matter
(run* [q]
      (fresh [x]
             (== x true)
             (== true q)))


;; x will be bound to _0 which is a symbol representing a fresh variable
(run* [x]
      s#)

(run* [x]
      (let [x false]
        (fresh [x]
               (== x true))))


(run* [r]
      (fresh [x y]
             (== (cons x (cons y '())) r)))


(run* [s]
      (fresh [t u]
             (== (cons t (cons u '())) s)))


(run* [r]
      (fresh [x]
             (let [y x]
               (fresh [x]
             (== (cons x (cons y (cons x '()))) r)))))

(run* [r]
      (fresh [x]
             (let [y x]
               (fresh [x]
                      (== (cons y (cons x (cons y '()))) r)))))

(run* [q]
      (== false q) ;; binds q to false
      (== true q)) ;; cannot bind q to true since q is not fresh

;; => (false) since although q is not fresh for the second statment, it is bound to false already
(run* [q]
      (== false q)
      (== false q))

(run* [q]
      (let [x q]
        (== true x))) ;; q and x are the same because of the let statment

(run* [q]
      (fresh [x]
        (== q x))) ;; q and x co-refer or share


(run* [q]
      (fresh [x] 
        (== true x)
        (== q x)))

(run* [q]
      (fresh [x]
       (== q x) ;; this ensures that whaever value x gets q will also get
       (== true x)))

(run* [q]
      (fresh [x]
       (== q x) ;; this ensures that whaever value x gets q will also get but the two variables are not equal
       (== (= q x) q)))

(run* [q]
      (fresh [x]
             (== q x) ;; this ensures that whaever value x gets q will also get but the two variables are not identical
             (== (identical? q x) q)))

(run* [q]
      (cond
        false s#
        :else u#))

(run* [q]
      (cond
        true s#
        :else u#))

;; both conde conditions are usuccefful therefore the result if ()
(run* [q]
      (conde
        [u# s#]  
        [s# u#]))

(run* [q]
      (conde
        [u# u#]
        [s# s#])) ;; this condition succeeds therefore the result is (_.0) since q is not involved

(run* [q]
      (conde
        [s# s#] ;; this will succeed 
        [s# u#]))

;;;
;; The Law of conde
; To get more values from conde, pretend that the successful conde line has failed, refreshing all
; variables that got an association from that line.
;;;
(run* [x]
      (conde
        [(== 'olive x) s#]
        [(== 'oil x) s#]))
;;=> (olive oil) 

;; The e in conde stands for "every line" since very line can succeed.

(run 1 [x]
      (conde
        [(== 'oil x) s#]
        [(== 'olive x) s#]))

(run* [x]
     (conde
       [(== 'virgin x) u#]
       [s#] ;; This will result in an unbound value _0
       [(== 'oil x) s#]
       [(== 'olive x) s#]))


(run 2 [x]
      (conde
        [(== 'extra x) s#]
        [(== 'virgin x) u#]
        [(== 'oil x) s#]
        [(== 'olive x) s#]))

(run* [r]
      (fresh [x y]
             (== 'split x)
             (== 'pea y)
             (== (cons x (cons y '())) r)))

(run* [r]
      (fresh [x y]
             (conde 
               [(== 'split x) (== 'pea y)]
               [(== 'navy x) (== 'bean y)])
             (== (cons x (cons y '())) r)))

(run* [r]
      (fresh [x y]
             (conde
               [(== 'split x) (== 'pea y)]
               [(== 'navy x) (== 'bean y)])
             (== (cons x (cons y (cons 'soup '()))) r)))

;; defining logic inside a function
(defn teacupo [x]
  (conde
   [(== 'tea x)]
   [(== 'cup x)]))

;; calling that logic funtion iside the run*
(run* [q]
      (teacupo q))


(run* [r]
      (fresh [x y]
             (conde
               [(teacupo x) (== true y) s#] ;; (tea true) and (cup true)
               [(== false x) (== true y)]) ;; (false true)
             (== (cons x (cons y '())) r)))


(run* [r]
      (fresh [x y z]
             (conde
               [(== y x) (fresh [x] (== x z))]
               [(fresh [x] (== x y)) (== x z)])
             (== (cons y (cons z '())) r)))

(run* [r]
      (fresh [x y z]
             (conde
               [(== y x) (fresh [x] (== x z))]
               [(fresh [x] (== x y)) (== x z)])
             (== false x)
             (== (cons y (cons z '())) r)))

(run* [r]
  (fresh [x]
     (fresh [x] (== x 1)) ;; can have a new fresh local variable
     (== r x)))

(run* [r]
  (let [a (== r true)
        b (== r false)]
    b))


(run* [q]
      (let [a (== q 1)
            b (fresh [x]
               (== 2 x)
               (== x q))
            c (conde 
                [(== 3 q)])]
        b))
