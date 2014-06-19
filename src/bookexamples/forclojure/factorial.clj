(ns bookexamples.forclojure.factorial)

(defn fact [n]
	(reduce * (range 1 (inc n))))

(== 1 (fact 1))
(== 6 (fact 3))
(== 120 (fact 5))