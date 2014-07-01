(ns bookexamples.forclojure.big-divide)

;; First solution - does not work for large values of n
(defn big-divide [n a b]
	(letfn [(multiples [elem]
				(take-while #(< % n) 
						(map #(*' % elem) (iterate inc 1))))]
	(apply +' (into (into (set (multiples a)) (multiples b)) (multiples (* a b))))))


(comment

(load-file "src/bookexamples/forclojure/big-divide.clj")
(refer 'bookexamples.forclojure.big-divide)

(big-divide 100 5 7)
(big-divide 10 3 5)
(big-divide 1000 3 5)

(= 0 (big-divide 3 17 11))
(= 23 (big-divide 10 3 5))
(= 233168 (big-divide 1000 3 5))
(= "2333333316666668" (str (big-divide 100000000 3 5)))
(= "110389610389889610389610"
  (str (big-divide (* 10000 10000 10000) 7 11)))
(= "1277732511922987429116"
  (str (big-divide (* 10000 10000 10000) 757 809)))
(= "4530161696788274281"
  (str (big-divide (* 10000 10000 1000) 1597 3571)))

;; Some of the solutions on the web

(partial iterate
           (fn [nums]
             (vec
               (map +' (conj nums 0) (cons 0 nums)))))
               

)