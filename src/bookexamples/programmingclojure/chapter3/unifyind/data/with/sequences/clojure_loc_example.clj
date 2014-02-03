(ns bookexamples.programmingclojure.chapter3.unifyind.data.with.sequences.clojure-loc-example
  (:use [clojure.java.io :only [reader]])
  (:import [java.io File]))

(defn non-blank? 
  "Detects non blanc lines of code"
  [line]
  (if (re-find #"\S" line) true false))

(non-blank? "dsds")

(non-blank? "     ")

(defn non-svn? 
  "detects files that are not Subversion metadata."
  [file]
  (not (.contains (.getName file) ".svn")))

(non-svn? (new File ".svn" ))
(non-svn? (new File "blah.svn" ))
(non-svn? (new File "ajhdjs" ))

(defn clojure-source? 
  "Detects Clojure source code files"
  [file]
  (.endsWith (.getName file) ".clj"))

(clojure-source? (new File ".svn" ))
(clojure-source? (new File "sfs.clj" ))
(clojure-source? (new File "clj.svn" ))

(defn clojure-loc
  "Function that counts the lines of Clojure code in a directory tree."
  [file-base]
  (reduce + 
          (map 
            #(with-open [rdr (reader %)]
               (count 
                 (filter non-blank? (line-seq rdr))))
            (file-seq (File. file-base))
           )
   )
  )

(clojure-loc ".")
  
  
  
  
  
  
  