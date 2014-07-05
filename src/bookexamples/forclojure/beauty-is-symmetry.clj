(ns bookexamples.forclojure.beauty-is-symmetry)

(defn symmetric? [[_ l r]] 
	(letfn [(mirror [xs]
				(if (not (coll? xs)) xs
					(vector (first xs) (mirror (last xs)) (mirror (second xs)))))]
		(= (mirror l) r)))

(defn mirror [xs]
	(if (not (coll? xs)) xs
		(vector (first xs) (mirror (last xs)) (mirror (second xs)))))


(comment

(load-file "src/bookexamples/forclojure/beauty-is-symmetry.clj")
(refer 'bookexamples.forclojure.beauty-is-symmetry)

(= [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]
	(mirror [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]))
(mirror '(:b nil nil))


(= (symmetric? '(:a (:b nil nil) (:b nil nil))) true)
(= (symmetric? '(:a (:b nil nil) nil)) false)
(= (symmetric? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
          [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
   true)
(= (symmetric? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
          [2 [3 nil [4 [6 nil nil] nil]] nil]])
   false)
(= (symmetric? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
          [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
   false)



;; Some of the solutions on the web
(fn [[_ l r]]
    (letfn [(mirror [t]
              (when-let [[v l r] t]
                [v (mirror r) (mirror l)]))]
      (= l (mirror r))))

#(letfn [
    (flip [x] (if (nil? x) nil
        [(first x) (flip (nth x 2)) (flip (nth x 1))]))]
    (= % (flip %)))

)