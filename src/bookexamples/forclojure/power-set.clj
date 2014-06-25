(ns bookexamples.forclojure.power-set)

(defn power-set [xs]
	(letfn [(gen-next [xs acc]
				(if (contains? acc xs) 
					(conj acc #{})
					(recur xs (set (for [x xs ys acc] (conj ys x))))))]
	(gen-next xs #{#{}})))


(comment

(load-file "src/bookexamples/forclojure/power-set.clj")
(refer 'bookexamples.forclojure.power-set)

(defn gen-next [xs acc]
	(if (contains? acc xs) 
		(conj acc #{})
		(recur xs (set (for [x xs ys acc] (conj ys x))))))

(gen-next #{1 2 3} #{#{}})


(power-set #{1 2 3})
(= (power-set #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})
(= (power-set #{}) #{#{}})
(= (power-set #{1 2 3})
   #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
(= (count (power-set (into #{} (range 10)))) 1024)


;; Some of the solutions on the web
(not= true true false)
(not= true true)
(not= false false false)

)