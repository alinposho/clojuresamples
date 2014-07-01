
(ns bookexamples.forclojure.pascal-trapezoid)

;; First working solution
(defn pascal-trapezoid [xs]
	(iterate #(conj (vec (cons (first %) (map (partial apply +') (partition 2 1 %)))) (last %)) xs))

(defn pascal-trapezoid [xs]
	(iterate #(conj (into (vector (first %)) (map (partial apply +') (partition 2 1 %))) (last %)) xs))


(comment

(load-file "src/bookexamples/forclojure/pascal-trapezoid.clj")
(refer 'bookexamples.forclojure.pascal-trapezoid)

(second (pascal-trapezoid [2 3 2]))
(take 5 (pascal-trapezoid [1]))
(take 2 (pascal-trapezoid [3 1 2]))

(= (second (pascal-trapezoid [2 3 2])) [2 5 5 2])
(= (take 5 (pascal-trapezoid [1])) [[1] [1 1] [1 2 1] [1 3 3 1] [1 4 6 4 1]])
(= (take 2 (pascal-trapezoid [3 1 2])) [[3 1 2] [3 4 3 2]])
(= (take 100 (pascal-trapezoid [2 4 2])) (rest (take 101 (pascal-trapezoid [2 2]))))

;; Some of the solutions on the web

(partial iterate
           (fn [nums]
             (vec
               (map +' (conj nums 0) (cons 0 nums)))))
               

)