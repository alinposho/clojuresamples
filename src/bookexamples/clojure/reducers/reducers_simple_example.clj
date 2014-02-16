(ns bookexamples.clojure.reducers.reducers-simple-example
  [:require [clojure.core.reducers :as r]])

(def v (range 10000000))

;; You will probably want to run the first function multiple times to 
;; give the VM time to warmup
(time (reduce + (map inc (filter even? v))))
(time (reduce + (r/map inc (r/filter even? v))))
(time (r/fold + (r/map inc (r/filter even? v))))
