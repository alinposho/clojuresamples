(ns bookexamples.util.jar-digest
  (:require [digest :refer :all]
            [clojure.java.io :refer [as-file]]
            [clojure.java.shell :refer [sh]]
            [clojure.string :as str]
            [bookexamples.util.jar-searching :refer :all]
            [bookexamples.util.jar-matching :refer :all]))

(defn find-jars-in
  "Returns a file-seq of jars files in path"
  [path]
  (filter #(re-matches #".*\.jar" (.getPath %))
          (file-seq (as-file path))))

(defn find-in-maven-central
  "Checks whether the jars represented by the seq jar-seq are in Maven Central by computing SHA1 checksum.
  Returns a seq of tuples of file name and number of jars found in Maven Central that match the SHA1 checksum"
  [jar-seq]
  (map (fn [file] [(.getPath file) ((comp count-found-jars search-by-sha1 digest/sha1) file)])
       jar-seq))

(defn to-string [file-count-seq]
    (str/join "\n"
              (map (partial str/join " ")
                   file-count-seq)))


(comment

  (digest/sha1 (as-file "/Users/alin.posorovaschi/temp/ant-1.8.2.jar"))

  ((partial re-matches #".*.jar") "/Users/alin.posorovaschi/temp/antlr-2.7.6.jar")

  (count (find-jars-in "/Users/alin.posorovaschi/temp"))

  (map (fn [file] [file ((comp count-found-jars search-by-sha1 digest/sha1) file)])
       (find-jars-in "/Users/alin.posorovaschi/temp"))

  (println (str/join "\n"
                     (map (partial str/join " ")
                          ((comp find-in-maven-central find-jars-in) "/Users/alin.posorovaschi/temp"))))

  ((comp find-in-maven-central find-jars-in) "/Users/alin.posorovaschi/temp")

  ;; Find what jars from ccdev are on maven central
  (spit "/Users/alin.posorovaschi/build/esbdocs/rpm_jars/ccdev-maven-check.out"
        ((comp to-string find-in-maven-central find-jars-in) "/Users/alin.posorovaschi/build/code/trunk/esb/ccdev"))


  ;; The following do not work
  (sh "find" "/Users/alin.posorovaschi/temp" "-name '*.jar'")
  (-> (sh "find" "/Users/alin.posorovaschi/temp" "-name '*.jar'")
      (:out)
      (str/split #"\n"))

  )
