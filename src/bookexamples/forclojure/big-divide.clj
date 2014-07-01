(ns bookexamples.forclojure.big-divide)

;; First solution - does not work for large values of n
(defn big-divide [n a b]
	(letfn [(multiples [elem]
    				(take-while #(< % n) 
  						(map #(* % elem) (iterate inc 1N))))]
	(reduce + (into (set (multiples a)) (multiples b)))))


(defn big-divide [n a b]
  (letfn [(multiples 
            ([elem]
              (take-while #(< % n) 
                (map #(* % elem) (iterate inc 1N))))
            ([elem not-elem]
                (take-while #(< % n) 
                  (map #(* % elem) (filter #(not= 0 (mod % not-elem)) (iterate inc 1N))))))]
  (+ (reduce + (multiples a b)) (reduce + (multiples b a)) (reduce + (multiples (* a b))))))

;; Solution that works for large values of n
(defn big-divide [n a b]
  (if (> a b) 
    (recur n b a)
    (let [m (- n 1)
          qa (quot m a)
          qb (quot m b)
          qab (quot m (*' a b))]
          (+'
            (- 
              (/ (*' qa (inc qa) a) 2)
              (/ (*' qab (inc qab) a b) 2)
            )
            (/ (*' qb (inc qb) b) 2)))))


(comment

(load-file "src/bookexamples/forclojure/big-divide.clj")
(refer 'bookexamples.forclojure.big-divide)

(big-divide 100 5 7)
(big-divide 10 3 5)
(time (big-divide 1000 3 5))
(time(big-divide 1000000 3 5))
(time(big-divide 10000000 3 5))
(time(big-divide 100000000 3 5))
(time(big-divide 100000000 7 11))

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

(fn [n a b]
    (let [gauss (fn [x] (/ (* x (+ x 1)) 2))
          gsum (fn [x] (* x (gauss (quot (dec n) x))))]
      (- (+ (gsum a) (gsum b))
         (gsum (* a b)))))               

)