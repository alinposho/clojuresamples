(ns bookexamples.programmingclojure.chapter6.protocols.datatypes.programming-to-abstractions
  (:import (java.io FileInputStream InputStreamReader BufferedReader
                    FileOutputStream OutputStreamWriter BufferedWriter)))


(defn gulp [src]
  "Reads the contents of the src and returns the result as string"
  (let [sb (StringBuilder.)]
    (with-open [reader (-> src 
                           FileInputStream.
                           InputStreamReader.
                           BufferedReader.)]
    (loop [c (.read reader)]
      (if (neg? c)
        (str sb)
        (do 
          (.append sb (char c))
          (recur (.read reader))))))))


(gulp "resources/some-file.txt")

(defn expectorate [dst content]
  (with-open [writer (-> dst 
                         FileOutputStream.
                         OutputStreamWriter.
                         BufferedWriter.)]
    (.write writer content)))

(expectorate "resources/text-file.txt" "Some string to be put on the file")
(gulp "resources/text-file.txt")


(defn make-reader [src]
  (-> src FileInputStream. InputStreamReader. BufferedReader.)) 

(defn make-writer [dst]
  (-> dst FileOutputStream. OutputStreamWriter. BufferedWriter.)) 

;; Redefine gulp and expectorate in terms of our two new functions
(defn gulp [src]
  "Reads the contents of the src and returns the result as string"
  (let [sb (StringBuilder.)]
    (with-open [reader (make-reader src)]
    (loop [c (.read reader)]
      (if (neg? c)
        (str sb)
        (do 
          (.append sb (char c))
          (recur (.read reader))))))))


(defn expectorate [dst content]
  (with-open [writer (make-writer dst)]
    (.write writer content)))

(expectorate "resources/text-file.txt" "Some string to be put on the file using the new make-* funtions")
(gulp "resources/text-file.txt")

