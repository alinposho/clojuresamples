(ns bookexamples.forclojure.pascal-triangle)

(defn pascal-triangle [n]
  (nth 
    (iterate
         (fn [nums]
           (vec
             (map +' (conj nums 0) (cons 0 nums)))) [1]) (dec n)))
	

(comment

(load-file "src/bookexamples/forclojure/pascal-triangle.clj")
(refer 'bookexamples.forclojure.pascal-triangle)

(pascal-triangle 1)
(= (map pascal-triangle (range 1 6))
   [     [1]
        [1 1]
       [1 2 1]
      [1 3 3 1]
     [1 4 6 4 1]])

(= (pascal-triangle 11)
   [1 10 45 120 210 252 210 120 45 10 1])

;; Some of the solutions on the web

)