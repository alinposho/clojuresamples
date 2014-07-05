(ns bookexamples.forclojure.sum-of-square-of-digits)

(defn sum-of-square-of-digits [xs] 
	(letfn [(digits [x]
				(map #(Integer/valueOf (str %)) (str x)))
			(sum-square [xs] 
				(reduce + (map #(* % %) xs)))]
			(count (filter #(< % (sum-square (digits %))) xs))))

(comment

(load-file "src/bookexamples/forclojure/sum-of-square-of-digits.clj")
(refer 'bookexamples.forclojure.sum-of-square-of-digits)

(sum-of-square-of-digits (range 10))
(sum-of-square-of-digits (range 30))


;; Some of the solutions on the web
(letfn [
    (good [x] (< x (apply + (map (comp #(* % %) #(- (int %) 48)) (str x)))))]
    (comp count (partial filter good)))


)