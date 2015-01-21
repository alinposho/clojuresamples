(ns bookexamples.functional-thinking.chapter5.evolve.parsing-xml
  [:require [clojure.xml :refer :all]])


(def WEATHER-URI "http://weather.yahooapis.com/forecastrss?w=%d&u=f")
(defn get-location [city-code]
  (for [x (xml-seq (parse (format WEATHER-URI city-code)))
        :when (= :yweather:location (:tag x))]
    (str (:city (:attrs x)) "," (:region (:attrs x)))))
(defn get-temp [city-code]
  (for [x (xml-seq (parse (format WEATHER-URI city-code)))
        :when (= :yweather:condition (:tag x))]
    (:temp (:attrs x))))
(println "Temperature for " (get-location 12770744) "is " (get-temp 12770744))

(xml-seq (parse (format WEATHER-URI 12770744)))
