(ns bookexamples.programmingclojure.chapter7.macros.making-macros-simpler)

;; Define a simple chain operator that is equivalent to the "." dot operator
(defmacro chain [x form]
  (list '. x form))

(def lst (java.util.ArrayList.))
(chain lst isEmpty)
(. lst add "element")

;; Extend chain to be equivalent to ..

;; This will not work
;(defmacro chain [x & form]
;  (list ('chain ('. x (first form) (rest form)))))

(defmacro chain 
  ([x form] 
     (list '. x form))
  ([x form & more] 
    (concat 
      (list 'chain (list '. x form)) 
      more)))


(macroexpand '(chain lst toArray length))

;; This will fail to execute since "length" is a field on the array class
;; whereas the dot operator expects a method
;(chain lst toArray length)

;; But this will work
(chain lst iterator next)

(macroexpand '(chain lst iterator next length))
(chain lst iterator next length)



