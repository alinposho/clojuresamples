(ns bookexamples.forclojure.fun-with-map)

(= '(6 7 8) (map #(+ % 5) '(1 2 3)))

(def m {1 :a 2 :b 3 nil})
(def k 6)

(defn contains-null-value [k m]
	(and (not (empty? (select-keys m [k]))) (nil? (m k))))

(contains-null-value 3 m) ;; true
(contains-null-value 4 m) ;; false



;;; Interleave Two Seqs
(defn my-interleave [xs ys] 
	(letfn [(intrlv [acc [x & xs] [y & ys]]
				(if (or (nil? x) (nil? y)) (reverse acc)
					(recur (conj acc x y) xs ys)))]
	(intrlv '() xs ys)))

(my-interleave [1 2 3] '(:a :b))

(mapcat #(list %1 %2) [1 2 3] '(:a :b))