(ns bookexamples.forclojure.find-distinct)

;; Solution one does not maintain ordering
(defn find-distinct [xs] 
	(keys (group-by identity xs)))

;; Works but it is highly inefficient
(defn find-distinct [xs] 
	(letfn [(conj-if-distinct [ys y]
				(if (contains? (set ys) y) ys (conj ys y)))]
	(reduce conj-if-distinct [] xs)))

;; WOrks and it performs somewhat better in terms of space
(defn find-distinct [xs] 
	(letfn [(conj-if-distinct [ys y]
				(if (some #(= % y) ys) ys (conj ys y)))]
	(reduce conj-if-distinct [] xs)))


(comment

(load-file "src/bookexamples/forclojure/find-distinct.clj")
(refer 'bookexamples.forclojure.find-distinct)

(= (find-distinct (range 50)) (range 50)) 
(find-distinct [1 2 1 3 1 2 4])


;; Some of the solutions on the web
(defn sol1 [xs]
	(reduce (fn [s e]
			  (if (some #(= % e) s)
				  s
				  (conj s e)))
		[] xs))

(sol1 [1 2 1 3 1 2 4])

(comp keys (partial sort-by (comp first first last)) (partial group-by last) (partial map-indexed list))


)