(ns bookexamples.util.jar-searching
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [cemerick.url :refer [url url-encode]]))


(defn search-by-sha1
  "Searches maven central for available jars based on the SHA1 checksum of the jar file"
  [jar-sha1]
  (let [maven-req-url (-> (url "http://search.maven.org/solrsearch/select")
                          (assoc :query (str "q=1:"
                                             "\""
                                             jar-sha1
                                             "\""
                                             "&rows=20&wt=json"))
                          str)]
    (->> (client/get maven-req-url {:throw-entire-message? true})
         :body
         (json/read-str))))

(defn count-found-jars [maven-central-response-map]
  "Extaracts the number of jars found on maven central based on jar SHA1 checksum"
  (get-in maven-central-response-map ["response" "numFound"]))


(comment

  (search-by-sha1 "fc33bf7cd8c5309dd7b81228e8626515ee42efd9")
  ((comp count-found-jars search-by-sha1) "fc33bf7cd8c5309dd7b81228e8626515ee42efd9")

  )
