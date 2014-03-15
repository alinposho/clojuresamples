(ns bookexamples.programmingclojure.chapter9.real.world.example.pinger-config
  (:use [clojure.java.io :only (reader resource)])
  (:require [clojure.string :as str])
  (:import (java.util Properties)))

(defn load-properties [src]
  (with-open [rdr (reader src)]
    (doto (Properties.)
      (.load rdr))))

;(def props (load-properties "resources/pinger.properties"))
;props
;(.getProperty props "urls")

(defn config
  []
  (load-properties "resources/pinger.properties"))

;(urls "http://google.com,http://amazon.com,http://google.com/badurl")

(defn urls [props]
    (str/split (.get props "urls") #","))

;(urls (load-properties "resources/pinger.properties"))
;(urls (config))