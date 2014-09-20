(ns bookexamples.forclojure.palindromic-numbers)

;; Problem 150:
;; A palindromic number is a number that is the same when written forwards or backwards (e.g., 3, 99, 14341).
;; Write a function which takes an integer n, as its only argument, and returns an increasing lazy sequence of all
;; palindromic numbers that are not less than n.
;; The most simple solution will exceed the time limit!
(defn palindroms [n]
  (rest (iterate next-palindrome (dec n))))

;;(take 4 (palindroms 10))

(defn to-number [rs]
  (loop [res (last rs)
  			 pow 10
  			 r (rest (reverse rs))]
	  (if (empty? r) res
	    (recur (+ res (* pow (first r))) (* 10 pow) (rest r)))))

(defn to-base [n base]
  (loop [xs (list (mod n base))
          n (quot n base)]
    (if (zero? n) xs
      (recur (cons (mod n base) xs) (quot n base)))))

(defn next-palindrome [n]
	(let [s (to-base n 10)
				half (drop (quot (count s) 2) s)
				nxt (+ 1 (to-number (reverse half)))
				[h & t :as all] (reverse (to-base nxt 10))
        nhalf (if (< (count half) (count all)) t all)]
		(to-number
			(if (odd? (count s))
				(concat (reverse t) nhalf)
				(concat (reverse all) nhalf)))))

(comment

(load-file "src/bookexamples/forclojure/palindromic-numbers.clj")
(refer 'bookexamples.forclojure.palindromic-numbers)


;; Some of the solutions on the web

)
