(ns bookexamples.programmingclojure.chapter9.real.world.example.pinger-main
  [:use [bookexamples.programmingclojure.chapter9.real.world.example.pinger]]
  [:use [bookexamples.programmingclojure.chapter9.real.world.example.pinger-scheduler :as scheduler]]
  (:gen-class))

(def immediately 0)
(def every-minute (* 60 1000))
(def every-3-seonds (* 3 1000))

(defn start 
  "REPL helper. Start pinger on executor e."  
  [e]
  (scheduler/periodically e check :initial-delay immediately :delay every-3-seonds))

(defn stop 
  "REPL helper. Shutdown executor e."  
  [e]
  (scheduler/shutdown-executor e))

(defn -main [] 
  (start (scheduler/scheduled-executor 1)))


;(-main)
;(def e (scheduler/scheduled-executor 1))
;(start e)
;(stop e)
