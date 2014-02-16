(ns bookexamples.clojure.reducers.timed-reducer-example
  [:require [clojure.core.reducers :as r]])

;; You might want to be patient since initializing and running this namespace will
;; take quite a few seconds

(def v (range 10000000))

(defn seq-reduce []
  (reduce + (map inc (filter even? v))))

(defn time-to-run-in-millis [func]
  (let [start (System/nanoTime)]
    (do 
      (func)
      (/ (- (System/nanoTime) start) 1000000.0))))

(time-to-run-in-millis seq-reduce)

(defn average [lst] (/ (reduce + lst) (count lst)))

(defn avg-time [runs func]
 "Computes the average running time of the function in runs number or runs"
 (average (repeat runs (time-to-run-in-millis func))))

;; warmup
(repeat 3 (time-to-run-in-millis seq-reduce))

;; If you have a look at the results you will notice that there is no difference 
;; between using fold and reduce
(list 
  (avg-time 5 #(reduce + (map inc (filter even? v))))
  (avg-time 5 #(reduce + (r/map inc (r/filter even? v))))
  (avg-time 5 #(r/fold + (r/map inc (r/filter even? v)))))

(defn prime? [n]
  (not-any? zero? (map #(rem n %) (range 2 (Math/sqrt n)))))

(def v (range 100000))

(list 
  (avg-time 5 #(reduce + (map inc (filter prime? v))))
  (avg-time 5 #(reduce + (r/map inc (r/filter prime? v))))
  (avg-time 5 #(r/fold + (r/map inc (r/filter prime? v)))))




