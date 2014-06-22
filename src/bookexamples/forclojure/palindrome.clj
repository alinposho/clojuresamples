(ns bookexamples.forclojure.palindrome)

(defn my-palindrome [xs]
	(= (seq xs) (reverse (seq xs))))


(comment

(load-file "src/bookexamples/forclojure/palindrome.clj")
(refer 'bookexamples.forclojure.palindrome)

(my-palindrome [1 2 1])
(my-palindrome "asd")
(my-palindrome "abcdcba")


;; Some of the solutions on the web
(#(= (reverse %) (seq %)) "abcdcba")

)