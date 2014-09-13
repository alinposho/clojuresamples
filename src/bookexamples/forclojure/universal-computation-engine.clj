(ns bookexamples.forclojure.universal-computation-engine)

;; Problem 121: Given a mathematical formula in prefix notation, return a function that calculates the value of the formula. The formula can contain nested calculations using the four basic mathematical operators, numeric constants, and symbols representing variables. The returned function has to accept a single parameter containing the map of variable names to their values.
(defn compute [body]
  (fn [params]
    (eval `(let [~@(flatten (vec params))] ~body))))
  
;; Some of my trials
(eval `(let [~@'(a 1 b 2)] ~'(+ a b)))
(eval `(let [~@(flatten (vec '{a 1 b 2}))] ~'(+ a b)))

(flatten (vec '{a 1 b 2}))

(comment

(load-file "src/bookexamples/forclojure/universal-computation-engine.clj")
(refer 'bookexamples.forclojure.universal-computation-engine)

(= 2 ((compute '(/ a b))
      '{b 8 a 16}))
(= 8 ((compute '(+ a b 2))
      '{a 2 b 4}))
(= [6 0 -4]
     (map (compute '(* (+ 2 a)
                  (- 10 b)))
          '[{a 1 b 8}
            {b 5 a -2}
            {a 2 b 11}]))
(= 1 ((compute '(/ (+ x 2)
              (* 3 (+ y 1))))
      '{x 4 y 1}))


;; Some of the solutions on the web

)