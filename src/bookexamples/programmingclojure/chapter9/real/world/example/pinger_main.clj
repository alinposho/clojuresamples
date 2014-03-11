(ns bookexamples.programmingclojure.chapter9.real.world.example.pinger-main
  [:use [bookexamples.programmingclojure.chapter9.real.world.example.pinger]])

(defn -main [] 
  (let [addresses '("http://www.google.com"
                     "http://www.amazon.com"
                     "http://www.google.com/badurl")]
  (while true
    (doseq [address addresses]
      (println (str address " available? " (available? address))))
    (java.lang.Thread/sleep (* 1000 60)))))

;(-main)