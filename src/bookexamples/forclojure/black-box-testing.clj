(ns bookexamples.forclojure.black-box-testing)

(defn coll [c]
	(if (associative? c)
		(if (not (reversible? c)) :map :vector)
		(if (apply distinct? (conj c 1 1)) :set :list)))

(map coll [{} #{} [] ()]))

(defn coll2 [c]
	({() :list  #{} :set {} :map} (empty c) 
		(when (reversible? c) :vector)))

(map coll2 [{} #{} [] ()])

(defn coll3 [c]
	((comp #(cond (= % {}) :map 
				 (= % #{}) :set 
				 (= (conj % 1 2) [1 2]) :vector 
				 true :list) 
	 empty) c))

(map coll3 [{} #{} [] ()])