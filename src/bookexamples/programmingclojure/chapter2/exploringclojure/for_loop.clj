(ns bookexamples.programmingclojure.chapter2.exploringclojure.for-loop)

(defn indexed [coll] (map-indexed vector coll))
(indexed "abcd")
(vec "kjafshjsh")

(defn index-filter [pred coll]
  (when pred
    (for [[idx elt] (indexed coll) :when (pred elt)] idx)))

(index-filter #{\a \b} "abcdbbb")

(index-filter #{\a \b} "xyz")

(defn index-of-any [pred coll]
  (first (index-filter pred coll)))
(index-of-any #{\a} "ajdhjs")


(nth (index-filter #{:h} [:t :t :h :t :h :t :t :t :h :h])
     2)

(meta #'str)

