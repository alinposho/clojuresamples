(ns bookexamples.programmingclojure.chapter3.unifyind.data.with.sequences.clojure-make-java-seq-able
  (:use [clojure.string :only [join]]
        [clojure.java.io :only [reader]])
  (:import (java.io File)))

(first (.getBytes "hello"))

(rest (.getBytes "hello"))

(doall (str (map char (cons (int \h) (.getBytes "ello")))))


(join (map char (cons (int \h) (.getBytes "ello"))))

(first "Hello")

; Don't do this
(let [m (re-matcher #"\w+" "the quick brown fox")] 
  (loop [match (re-find m)]    (when match      (println match)    (recur (re-find m)))))

;; Do not do this. This is the imperative way of doing things
(def m (re-matcher #"\w+" "the quick brown fox"))
(re-find m)

(re-seq #"\w+" "the quick brown fox")

(seq (.listFiles (File. ".")) )
;; No need to call seq once we call map
(map #(.getName %) (seq (.listFiles (File. "."))) )
(map #(.getName %) (.listFiles (File. ".")))

(count (file-seq (File. ".")))

;; Recently changed files
(defn minutes-to-millis [mins] 
  (* mins 60 1000))

(minutes-to-millis 10)

(defn recently-modified? [file]
  (> (.lastModified file) (- (java.lang.System/currentTimeMillis) (minutes-to-millis 30))))

;; Files that have been changed in the last 30 minutes
(map #(.getName %) (filter recently-modified? (file-seq (File. "."))))
(map #(.getName %) (take-while recently-modified? (file-seq (File. "."))))


;; Seq-ing a Stream
(take 2 (line-seq (reader "src/bookexamples/programmingclojure/chapter3/unifyind/data/with/sequences/clojure_make_java_seq_able.clj")))
;; Count the lines in a file with safe opening and closing the reader
(with-open [rdr (reader "src/bookexamples/programmingclojure/chapter3/unifyind/data/with/sequences/clojure_make_java_seq_able.clj")]
  (count 
    (line-seq rdr)))
;; Count the non blank lines in the file
(with-open [rdr (reader "src/bookexamples/programmingclojure/chapter3/unifyind/data/with/sequences/clojure_make_java_seq_able.clj")]
  (count 
    (filter #(re-find #"\S" %) 
            (line-seq rdr))))









