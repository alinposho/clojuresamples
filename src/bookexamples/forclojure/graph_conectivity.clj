(ns bookexamples.forclojure.graph-conectivity)

(defn connected? [x]
  true)

(comment

(load-file "src/bookexamples/forclojure/infix.clj")
(refer 'bookexamples.forclojure.infix)

;; Some of the solutions on the web
(fn infix [init & rawops] (let [
                                ops (partition 2 rawops)
                                do-op (fn [v op] ((first op) v (second op)))]
                            (reduce do-op init ops)))

(fn [f & r]
  (reduce (fn [l [o r]]
            (o l r))
          f
          (partition 2 r)))

(fn infix
  ([x op y] (op x y))
  ([x op y & xs]
   (apply infix (cons (infix x op y)
                      xs))))
)
