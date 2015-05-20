(ns bookexamples.util.collections)

(defn foldlr [f1 f2 l r xs]
  (if (seq xs)
    (f2 l (first xs) (foldlr f1 f2 (f1 l (first xs)) r (rest xs)))
    r))


(conj [12 2] 2)