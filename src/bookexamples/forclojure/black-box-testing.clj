(ns bookexamples.forclojure.black-box-testing)

(defn coll [c]
	(if (associative? c)
		(if (not (reversible? c)) :map :vector)
		(if (apply distinct? (conj c 1 1)) :set :list)))

(map coll [{} #{} [] ()]))

(defn coll2 [c]
	({() :list  #{} :set {} :map} (empty c) (when (reversible? c) :vector)))

(map coll2 [{} #{} [] ()])
