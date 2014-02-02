(ns bookexamples.programmingclojure.getting.started.is-blank-refactoring)

(defn blank? [str]
  (every? #(Character/isWhitespace %) str))


(blank? "           ")
(blank? nil)
(blank? "    kj       ")