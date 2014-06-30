(ns bookexamples.forclojure.parse-binary-int)

(defn parse-binary-int [x]
	(Integer/parseInt x 2))

(comment

(load-file "src/bookexamples/forclojure/parse-binary-int.clj")
(refer 'bookexamples.forclojure.parse-binary-int)

(parse-binary-int "111")

;; Some of the solutions on the web
(#(read-string (str 2 \r %)) "111") ;; does not work
(reduce #(+ %1 %1 (- (int %2) 48)) 0 "111")
)