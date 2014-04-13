(ns the-joy-of-clojure.chapter11-common
	[:import [java.util.concurrent Executors]])


;; Let's get a hang of how "dotimes" works
(dotimes [n 5]
	(println "n is " n))

(def n 10)
;; Notice that the n that we have bound to 10 is not affected by the call to dotimes
(dotimes [counter n]
	(println "counter is " counter)
	(println "n is " n))

(def ^:dynamic *pool* 
	(Executors/newFixedThreadPool (-> (Runtime/getRuntime) .availableProcessors (+ 2))))
(.shutdown *pool*)

; (.isTerminated *pool*)
 (defn dothread! 
 	[f & {thread-count :threads, exec-count :times
 		  or {thread-count 1 exec-count 1}}]
 		(dotimes [t thread-count]
 			(.submit *pool* #(dotimes [_ exec-count] f))))

(dothread! #(println "n is " (inc n)) {:threads 3, :times 3})





