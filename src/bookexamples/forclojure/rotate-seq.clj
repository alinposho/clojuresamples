(ns bookexamples.forclojure.rotate-seq)

(defn rotate-seq [n xs]
	(let [xc (count xs)
		  n (mod n xc)]
	(take xc (drop n (take (* 2 n xc) (apply concat (repeat xs)))))))

(comment

(load-file "src/bookexamples/forclojure/rotate-seq.clj")
(refer 'bookexamples.forclojure.rotate-seq)

(= (rotate-seq 2 [1 2 3 4 5]) '(3 4 5 1 2))
(= (rotate-seq -2 [1 2 3 4 5]) '(4 5 1 2 3))
(= (rotate-seq 6 [1 2 3 4 5]) '(2 3 4 5 1))
(= (rotate-seq 1 '(:a :b :c)) '(:b :c :a))
(= (rotate-seq -4 '(:a :b :c)) '(:c :a :b))

(rotate-seq -2 [1 2 3 4 5])

;; Some of the solutions on the web
(def f1 
	(fn [n coll]
	    (let [idx (mod n (count coll))]
	      (concat (drop idx coll) (take idx coll)))))
(f1 2 [1 2 3 4 5])
(f1 -2 [1 2 3 4 5])


)