(ns bookexamples.forclojure.filter-uppercase)

(defn my-filter-uppercase [xs]
	(apply str (filter #(Character/isUpperCase %) xs)))

(comment

(load-file "src/bookexamples/forclojure/filter-uppercase.clj")
(refer 'bookexamples.forclojure.filter-uppercase)

(my-filter-uppercase "Hello World!")

;; Some of the solutions on the web
(#(apply str (re-seq #"[A-Z]" %)) "AbjsdhsjB KL")
(#(clojure.string/replace % #"[^A-Z]" "") "js JH kjd L LL")

)