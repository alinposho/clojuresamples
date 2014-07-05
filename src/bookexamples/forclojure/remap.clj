(ns bookexamples.forclojure.remap)

(defn remap [f xs] 
  (if (seq xs)
      (cons (f (first xs)) (lazy-seq (remap f (rest xs))))))


(comment

(load-file "src/bookexamples/forclojure/remap.clj")
(refer 'bookexamples.forclojure.remap)

(remap inc [1 2 3])


;; Some of the solutions on the web
(fn [f x] (rest (reductions #(f %2) nil x)))


)