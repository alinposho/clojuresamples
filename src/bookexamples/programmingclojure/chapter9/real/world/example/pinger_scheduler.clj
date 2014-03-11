(ns bookexamples.programmingclojure.chapter9.real.world.example.pinger-scheduler
  (:import (java.util.concurrent ThreadPoolExecutor ScheduledThreadPoolExecutor TimeUnit)))

(defn scheduled-executor 
  "Create a scheduled executor."
  ^ScheduledThreadPoolExecutor [threads]
  (ScheduledThreadPoolExecutor. threads))

(defn periodically
  "Schedules function f to run on executor e every 'delay'
   milliseconds after a delay of 'initial-delay' Returns
   a ScheduledFuture."
  ^ScheduledFuture [e f & {:keys [initial-delay delay]}]
  (.scheduleWithFixedDelay 
    e f 
    initial-delay delay
    TimeUnit/MILLISECONDS))

(defn shutdown-executor 
  "Shutdown an executor"
  [^ScheduledThreadPoolExecutor e]
  (.shutdown e))

;(def e (scheduled-executor 2))
;
;(defn print-smth []
;  (println "Hello from task running on executor"))
;
;(periodically e print-smth :initial-delay 3000 :delay 2000)
;
;(shutdown-executor e)


