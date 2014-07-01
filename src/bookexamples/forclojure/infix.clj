(ns bookexamples.forclojure.infix)

(defn infix [x & xs] 
	(reduce #((first %2) %1 (second %2)) x (partition-all 2 xs)))

(comment

(load-file "src/bookexamples/forclojure/infix.clj")
(refer 'bookexamples.forclojure.infix)

(= 7  (infix 2 + 5))
(= 42 (infix 38 + 48 - 2 / 2))
(= 8  (infix 10 / 2 - 1 * 2))
(= 72 (infix 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))

(infix )

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