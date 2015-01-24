(ns bookexamples.functional-thinking.memoization
  (:require [clojure.string :as str]))

(let [alpha (into #{} (concat (map char (range (int \a) (inc (int \z))))
                              (map char (range (int \A) (inc (int \Z))))))
      rot13-map (zipmap alpha (take 52 (drop 26 (cycle alpha))))]
  (defn rot13
    "Given an input string, produce the rot 13 version of
    the string. \"hello\" -> \"uryyb\""
    [s]
    (apply str (map #(get rot13-map % %) s))))


(defn name-hash [name]
  (apply str (map #(rot13 %) (str/split name #"\d"))))

(def name-hash-m (memoize name-hash))

(rot13 "hello")
(rot13 (str/upper-case "hello"))

(name-hash "Blah")
