(ns bookexamples.programmingclojure.chapter3.unifyind.data.with.sequences.xml-sequencing
  (:use [clojure.xml :only [parse]]))

(parse (java.io.File. "src/resources/compositions.xml"))
(xml-seq (parse (java.io.File. "src/resources/compositions.xml")))

(defn get-composers [file-name] 
  (for [x (xml-seq
            (parse (java.io.File. file-name)))
        :when (= :composition (:tag x))] (:composer (:attrs x)))
  )

(get-composers "src/resources/compositions.xml")