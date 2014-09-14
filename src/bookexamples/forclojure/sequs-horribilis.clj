(ns bookexamples.forclojure.sequs-horribilis)

;; Problem 112: 
;; Create a function which takes an integer and a nested collection of integers as arguments. Analyze the elements of the input collection and return a sequence which maintains the nested structure, and which includes all elements starting from the head whose sum is less than or equal to the input integer.
(defn sequs [n sq]
	(letfn [(append-rst [pres sum [hd & tl]] 
						(cond
							(coll? hd) 
								(let [elem (sequs (- n sum) (vec hd))]
									(recur (conj pres elem) (apply + (flatten elem)) tl))
							(or (nil? hd) (> (+ sum hd) n )) pres
							:else (recur (conj pres hd) (+ sum hd) tl)))]
		(when-not (nil? sq)) (append-rst [] 0 sq)))

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
(fn __ 
    ([limit col] (__ limit [] col))
    ([limit agg [x & xs]]
     (cond
       (not x) agg
       (sequential? x) 
       (let [xsum (->> (flatten x) (reduce +))]
         (if (<= xsum limit)
           (__ (- limit xsum)
               (conj agg x)
               xs)
           (conj agg
                 (__ limit
                     x))))
       (> x limit) agg
       :else (__ (- limit x) (conj agg x) xs)))
    )

(fn [max-sum coll]
  (letfn [(step [coll current-sum max-sum]
            (if (seq coll)
              (let [head (first coll)]
                (if (coll? head)
                  (let [sub (step head current-sum max-sum)
                        next-sum (+ current-sum (reduce + (flatten sub)))]
                    (if (<= next-sum max-sum)
                      (cons sub (step (next coll) next-sum max-sum))
                      sub))
                  (let [next-sum (+ head current-sum)]
                    (if (<= next-sum max-sum)
                      (cons head (step (next coll) next-sum max-sum))
                      []))))))]
    (step coll 0 max-sum)))

(letfn [
    (horribili [x t] (cond 
        (number? t) (cond 
            (>= x t) [(- x t) [t]] 
            true     [nil []])
        (empty? t)  [x [[]]]
        true        (let 
            [[xf tf] (horribili x (first t))]
            (cond 
                (nil? xf) [nil [tf]] 
                true      (let 
                    [[xn tn] (horribili xf (rest t))]
                    [xn [(concat tf (first tn))]])))))]
    (comp first second horribili))


)