(ns bookexamples.forclojure.happy-number?)

(defn happy-number? [x]
	(let [unhappy-cycle #{4 16 37 58 89 145 42 20}
		  digits (map #(Integer/valueOf (str %)) (str x))
		  sum (reduce + (map #(* % %) digits))]
		(cond 
			(= sum 1) true
			(unhappy-cycle sum) false
			:else (recur sum))))

(comment

(load-file "src/bookexamples/forclojure/happy-number?.clj")
(refer 'bookexamples.forclojure.happy-number?)

(map happy-number? #{4 16 37 58 89 145 42 20})

(= (happy-number? 7) true)
(= (happy-number? 986543210) true)
(= (happy-number? 3) false)
(= (happy-number? 2) false)

;; Some of the solutions on the web
(letfn [(num->digits [num]
          (loop [n num res []]
            (if (zero? n)
              res
              (recur (long (/ n 10)) (cons (mod n 10) res)))))
        (change [n]
          (apply + (map #(* % %) (num->digits n))))]
  (fn [init]
    (loop [curr init results #{}]
      (println curr " - " results)
      (cond
       (= 1 curr) true
       (results curr) false
       :else (let [new-n (change curr)]
               (println curr new-n)
               (recur new-n (into results [curr])))
       )))
  )

(letfn [
    (happy [x] (apply + (for [
        i (str x)] 
        (#(* % %) (- (int i) 48)))))
    (happy? [s x] (cond
        (s x) false
        (= 1 x) true
        true (recur (conj s x) (happy x))))]
    happy?) #{}
)