(ns bookexamples.programmingclojure.chapter2.exploringclojure.clojure-destructuring)

;; For more info about destructuring check the official documentation

;; This will result in [nil nil] because there are no keys :a and :b in the map
(let [{:keys [a b]} {"a" 1, "b" :p, :p "g"}]
  [a b])
;; However, this will work since we have the strings a and b as keys
(let [{:strs [a b]} {"a" 1, "b" :p, :p "g"}]
  [a b])
