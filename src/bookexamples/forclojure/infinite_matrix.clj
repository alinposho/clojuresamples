(ns bookexamples.forclojure.infinite-matrix)

;; Problem 168:
;;   In what follows, m, n, s, t denote nonnegative integers, f denotes a function that accepts two arguments and is defined for all nonnegative integers in both arguments.
;;
;; In mathematics, the function f can be interpreted as an infinite matrix with infinitely many rows and columns that, when written, looks like an ordinary matrix but its rows and columns cannot be written down completely, so are terminated with ellipses. In Clojure, such infinite matrix can be represented as an infinite lazy sequence of infinite lazy sequences, where the inner sequences represent rows.
;;
;; Write a function that accepts 1, 3 and 5 arguments
;;
;; with the argument f, it returns the infinite matrix A that has the entry in the i-th row and the j-th column equal to f(i,j) for i,j = 0,1,2,...;
;; with the arguments f, m, n, it returns the infinite matrix B that equals the remainder of the matrix A after the removal of the first m rows and the first n columns;
;; with the arguments f, m, n, s, t, it returns the finite s-by-t matrix that consists of the first t entries of each of the first s rows of the matrix B or, equivalently, that consists of the first s entries of each of the first t columns of the matrix B.
(defn infinite-matrix
  ([f] (infinite-matrix f 0 0))
  ([f m n]
   (cons
    (cons (f m n) (lazy-seq (infinite-matrix f m (inc n))))
    (lazy-seq (infinite-matrix f (inc m) n)))))


(take 5 (map #(take 6 %) (infinite-matrix str 3 4)))


(comment

(load-file "src/bookexamples/forclojure/infinite_matrix.clj")
(refer 'bookexamples.forclojure.infinite-matrix)


;; Some of the solutions on the web



)
