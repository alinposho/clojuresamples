(ns bookexamples.forclojure.decurry)

(defn decurry [fs] 
  (fn [& xs] 
    (reduce #(%1 %2) fs xs)))

(comment

(load-file "src/bookexamples/forclojure/decurry.clj")
(refer 'bookexamples.forclojure.decurry)

((decurry (fn [a] 
            (fn [b] 
              (fn [c]
                (+ a b c))))) 
  1 2 3)

(= 10 ((decurry (fn [a]
             (fn [b]
               (fn [c]
                 (fn [d]
                   (+ a b c d))))))
       1 2 3 4))

(= 24 ((decurry (fn [a]
             (fn [b]
               (fn [c]
                 (fn [d]
                   (* a b c d))))))
       1 2 3 4))

(= 25 ((decurry (fn [a]
             (fn [b]
               (* a b))))
       5 5))

;; Some of the solutions on the web
(fn decurry [init & rawops] (let [
    ops (partition 2 rawops)
    do-op (fn [v op] ((first op) v (second op)))]
    (reduce do-op init ops)))

(fn [f & r]
  (reduce (fn [l [o r]]
            (o l r))
            f
            (partition 2 r)))

(fn decurry 
  ([x op y] (op x y))
  ([x op y & xs]
   (apply decurry (cons (decurry x op y)
                xs))))

)