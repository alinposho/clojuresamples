(ns the-joy-of-clojure.infinite-sequences)

(defn triangle [n]
	(/ (* n (+ n 1)) 2))

(def tri-nums (map triangle (iterate inc 1)))

(last (take 1000 tri-nums))

(take 10 (filter even? tri-nums))

(double (reduce + (take 1000 (map / tri-nums))))
(take 10 (map / tri-nums))

;; This is interesting. It will create the 1/4 rational value:
(/ 4)
