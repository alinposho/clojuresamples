(ns bookexamples.programmingclojure.chapter7.macros.making-macros-simpler)

;; Define a simple chain operator that is equivalent to the "." dot operator
(defmacro chain [x form]
  (list '. x form))

(def lst (java.util.ArrayList.))
(chain list isEmpty)

;; Extend chain to be equivalent to ..

;; This will not work
;(defmacro chain [x & form]
;  (list ('chain ('. x (first form) (rest form)))))

(defmacro chain 
  ([x form] 
    (list '. x form))
  ([x form & more]
    (concat (list 'chain (list '. x form) more)) ))


(macroexpand '(chain lst toArray length))

(chain lst toArray length)