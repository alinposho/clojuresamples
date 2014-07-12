(ns bookexamples.forclojure.into-camel-case)

(defn camel-case [s]
  (let [[x & xs] (re-seq #"[a-zA-Z]+" s)]
    (clojure.string/join (cons x (map clojure.string/capitalize xs)))))




(comment

(load-file "src/bookexamples/forclojure/into-camel-case.clj")
(refer 'bookexamples.forclojure.into-camel-case)


(= (camel-case "multi-word-key") "multiWordKey")
(= (camel-case "something") "something")
(= (camel-case "leaveMeAlone") "leaveMeAlone")

;; Some of the solutions on the web
#(clojure.string/replace % #"-(\w)" (fn [[a b]] (clojure.string/capitalize b)))
#(clojure.string/replace % #"-[a-z]" (comp clojure.string/upper-case last))

)