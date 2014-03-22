(ns bookexamples.functional_programming_patterns.memoization)

(defn slow-fib 
	[n]
	(cond (<= n 0) 0
		(<= n 2) 1
		:else (+ (slow-fib (- n 1)) (slow-fib (- n 2)))))

; (time (slow-fib 40))

(def memo-fib 	
	(memoize 
		(fn [n]
			(cond (<= n 0) 0
				(<= n 2) 1
				:else (+ (memo-fib (- n 1)) (memo-fib (- n 2)))))))

;; A dramatic improvement in performance for this particular algorithm implementation
; (time (memo-fib 40))
