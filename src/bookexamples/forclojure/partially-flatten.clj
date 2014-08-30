(ns bookexamples.forclojure.partially-flatten)

;; Problem 93: Write a function which flattens any nested combination of sequential things (lists, vectors, etc.), but maintains the lowest level sequential items. The result should be a sequence of sequences with only one level of nesting.
(defn pflatten [xs]
  (letfn [(should-not-flatten? [xs] (not-any? coll? xs))]
    (reduce #(concat %1 (if (should-not-flatten? %2) [%2] (pflatten %2))) [] xs)))

(comment

(load-file "src/bookexamples/forclojure/partially-flatten.clj")
(refer 'bookexamples.forclojure.partially-flatten)

(= (pflatten [["Do"] ["Nothing"]])
   [["Do"] ["Nothing"]])
(= (pflatten [[[[:a :b]]] [[:c :d]] [:e :f]])
   [[:a :b] [:c :d] [:e :f]])
(= (pflatten '((1 2)((3 4)((((5 6)))))))
   '((1 2)(3 4)(5 6)))

;; Some of the solutions on the web
;; I don't want to know what this does
((fn _ [x]
          (if 
            (not (sequential? (first x))) [x]
            (reduce concat (map _ x))
            ))
'((1 2)((3 4)((((5 6)))))))

(fn [x] (filter #(= % (flatten %)) (tree-seq sequential? seq x)))

)