(ns bookexamples.programmingclojure.chapter9.real.world.example.pinger
  [:import [java.net URL HttpURLConnection]]
  [:require [clojure.tools.logging :as logger]
            [bookexamples.programmingclojure.chapter9.real.world.example.pinger-config :as config]])

(defn response-code [address]   (let [conn ^HttpURLConnection (.openConnection (URL. address)) 
         code (.getResponseCode conn)] 
      (when (< code 400) 
        (-> conn .getInputStream .close)) 
      code))
;(response-code "http://www.google.com")

(defn available? [address] 
  (= 200 (response-code address)))

;(available? "http://www.google.com")
;(available? "http://www.google.com/bad_url")

(defn record-availability [address]
  (if (available? address)
    (logger/info (str address " is responding normally"))
    (logger/error (str address " is not available"))))

(defn check [] 
    (doseq [address (config/urls (config/config))]
      (record-availability address)))

;(check)






