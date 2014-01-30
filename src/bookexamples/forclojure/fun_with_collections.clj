(ns bookexamples.forclojure.fun-with-collections)

(last [1 2 3 4])

(defn lastElem [x] 
  (if (empty? (rest x)) 
             (first x)
             (lastElem (rest x)))
  )

(fn lastElem [x] 
  (if (empty? (rest x)) 
             (first x)
             (lastElem (rest x))))

(lastElem {1 2, 3 4})
