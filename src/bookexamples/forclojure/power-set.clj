(ns bookexamples.forclojure.power-set)

(defn power-set [xs]
	(letfn [(gen-next [xs acc]
				(if (contains? acc xs) 
					(conj acc #{})
					(recur xs (set (for [x xs ys acc] (conj ys x))))))]
	(gen-next xs #{#{}})))

;; Intermediary function definition
(defn gen-next [xs acc]
	(if (contains? acc xs) 
		(conj acc #{})
		(recur xs (set (for [x xs ys acc] (conj ys x))))))


(comment

(load-file "src/bookexamples/forclojure/power-set.clj")
(refer 'bookexamples.forclojure.power-set)

(gen-next #{1 2 3} #{#{}})


(power-set #{1 2 3})
(= (power-set #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})
(= (power-set #{}) #{#{}})
(= (power-set #{1 2 3})
   #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
(= (count (power-set (into #{} (range 10)))) 1024)


;; Some of the solutions on the web
(fn power-set [coll]
    (letfn [(union [s1 s2] (reduce conj s1 s2))]
      (if (empty? coll)
        #{#{}}
        (let [element (first coll)
              sub1 (power-set (disj coll element))
              sub2 (set (map #(union #{element} %) sub1))]
          (union sub1 sub2)))))

(fn pow [s] (if (empty? s) #{s} (let [
    s1 (first s)
    sn (disj s s1)
    pp (pow sn)
    pn (for [i pp] (conj i s1))]
    (into pp pn)))

)