(ns bookexamples.forclojure.the-balance-of-n)

;; Problem 155: A balanced number is one whose component digits have the same sum on the 
;; left and right halves of the number. Write a function which accepts an integer n, and returns true if n is balanced.
(defn balanced? [n]
	(let [digits (map #(- (int %) 48) (str n))
        c (count digits)
        [l r] (split-at (quot c 2) digits)]
      (= (reduce + l) (reduce + (if (even? c) r (rest r))))))



(comment

(load-file "src/bookexamples/forclojure/the-balance-of-n.clj")
(refer 'bookexamples.forclojure.the-balance-of-n)

(balanced? 121)
(balanced? 1221)



;; Some of the solutions on the web
;; I don't want to know what this does
(fn [x]
  (let [n (map #(- (int %) 48) (str x))
        f #(apply + (take (/ (count n) 2) %))]
        (= (f n) (f (into () n)))))

#(let [
    xs (for [i (str %)] (- (int i) 48))
    sz (quot (count xs) 2)
    s1 (apply + (take sz xs))
    s2 (apply + (take-last sz xs))
    ] (= s1 s2))



)