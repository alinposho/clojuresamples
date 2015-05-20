(ns bookexamples.util.jar-searching
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [cemerick.url :refer [url url-encode]]))


(defn search-by-sha1 [jar-sha1]
  (let [maven-req-url (-> (url "http://search.maven.org/solrsearch/select")
                          (assoc :query (str "q=1:"
                                             "\""
                                             jar-sha1
                                             "\""
                                             "&rows=20&wt=json"))
                          str)]
    (get-in
      (->> (client/get maven-req-url {:throw-entire-message? true})
           :body
           (json/read-str))
      ["response" "numFound"])))

(comment
  (-> (url "http://search.maven.org")
      (assoc :path (str "/#search" (url-encode (str "|ga|1|1:\"fc33bf7cd8c5309dd7b81228e8626515ee42efd9\""))))
      str)
  (let [maven-req-url (-> (url "http://search.maven.org/solrsearch/select")
                          (assoc :query (str "q=1:"
                                             (url-encode "\"fc33bf7cd8c5309dd7b81228e8626515ee42efd\"")
                                             "&rows=20&wt=json"))
                          str)]
    (get-in
      (->> (client/get maven-req-url {:throw-entire-message? true})
           :body
           (json/read-str))
      ["response" "numFound"]))

  (get-in {"responseHeader" {"status" 0, "QTime" 1, "params" {"fl" "id,g,a,v,p,ec,timestamp,tags", "sort" "score desc,timestamp desc,g asc,a asc,v desc", "indent" "off", "q" "1:\"fc33bf7cd8c5309dd7b81228e8626515ee42efd\"", "wt" "json", "rows" "20", "version" "2.2"}}, "response" {"numFound" 0, "start" 0, "docs" []}}
          ["response" "numFound"])
  )
