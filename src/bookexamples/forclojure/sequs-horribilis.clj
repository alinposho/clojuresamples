(ns bookexamples.forclojure.sequs-horribilis)

;; Problem 112: 
;; Create a function which takes an integer and a nested collection of integers as arguments. Analyze the elements of the input collection and return a sequence which maintains the nested structure, and which includes all elements starting from the head whose sum is less than or equal to the input integer.

;; Does not work for lazy infinite collections
(defn sequs [n sq]
	(if (seq? (seq sq))
		(let [[prfx [hd & tl]] (split-with integer? sq)
					sum (apply + prfx)
					sub (sequs (- n sum)  hd)
					append-rst (fn [pres sum [hd & tl]] 
												(if (or (nil? hd) (> (+ sum hd) n )) 
													pres
													(recur (conj pres hd) (+ sum hd) tl)))
					pres (if (nil? sub) 
										(append-rst [(first prfx)] (first prfx) (rest prfx))
										(conj (vec prfx) sub))]
				; [prfx sub sm pres])))
		(append-rst (vec pres) (apply + (flatten pres)) tl)
				)))

(defn sequs [n sq]
	(letfn [(append-rst [pres sum [hd & tl]] 
						(cond
							(coll? hd) 
							(let [elem (sequs (- n sum) (vec hd))]
								(recur (conj pres elem) (apply + (flatten elem)) tl))
							(or (nil? hd) (> (+ sum hd) n )) pres
							:else (recur (conj pres hd) (+ sum hd) tl)))]
		(if (not (nil? sq))
			(append-rst [] 0 sq)
				)))

(sequs 10 [1 2 [3 [4 5] 6] 7])


(split-with integer? [1 2 [3 [4 5] 6] 7])

(comment

(load-file "src/bookexamples/forclojure/sequs-horribilis.clj")
(refer 'bookexamples.forclojure.sequs-horribilis)


(=  (sequs 10 [1 2 [3 [4 5] 6] 7])
   '(1 2 (3 (4))))
(=  (sequs 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])
   '(1 2 (3 (4 (5 (6 (7)))))))
(=  (sequs 9 (range))
   '(0 1 2 3))
(=  (sequs 1 [[[[[1]]]]])
   '(((((1))))))
(=  (sequs 0 [1 2 [3 [4 5] 6] 7])
   '())
(=  (sequs 0 [0 0 [0 [0]]])
   '(0 0 (0 (0))))
(=  (sequs 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])
   '(-10 (1 (2 3 (4)))))

;; Some of the solutions on the web

)