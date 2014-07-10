(ns bookexamples.forclojure.word-sorting)

(defn word-sorting [s] 
	(sort #(apply compare (map clojure.string/lower-case [%1 %2])) (re-seq #"[a-zA-Z]+" s)))

(comment

(load-file "src/bookexamples/forclojure/word-sorting.clj")
(refer 'bookexamples.forclojure.word-sorting)

(= (word-sorting  "Have a nice day.")
   ["a" "day" "Have" "nice"])
(= (word-sorting  "Clojure is a fun language!")
   ["a" "Clojure" "fun" "is" "language"])
(= (word-sorting  "Fools fall for foolish follies.")
   ["fall" "follies" "foolish" "Fools" "for"])

;; Some of the solutions on the web
#(sort-by (fn [v](.toLowerCase v))  (re-seq #"\w+" %
)