(ns bookexamples.programmingclojure.chapter7.macros.creating-name-ia-a-macro)

(defmacro bench [func]
  `(let [start# (System/nanoTime)
         result# ~func]
       {:result result# :elapsed (/ (- (System/nanoTime) start#) 1000000.0)}))

`foo#

(bench (+ 1 3))
(macroexpand '(bench (+ 1 3)))

;; Redefining the defstruct macro as a function does not work since the "def" form
;; cannot be used for dynamic calls at runtime
(defn my-defstruct
  [name & keys]
  (def name (create-struct keys)))

;; This will fail at runtime becase def cannot be used for dynamic calls
;;(my-defstruct person :first-name :last-name)


(#(list 'def %) 'a)

(map #(list 'def %) '[a, b, c, d] )

;; Interesting that this is a way of quoting all the elements of the vector 
'[a, b, c, d]
;; It's like doing this
['a, 'b, 'c, 'd]
(class '[a, b, c, d])

;; This outputs the same as the macroexpand command on the declare macro
`(do ~@(map #(list 'def %) '[a b c d]))

(macroexpand-1 '(declare a b c d))








