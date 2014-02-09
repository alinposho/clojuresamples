(ns bookexamples.programmingclojure.chapter5.state.failed-attempt-at-using-threads
  [:import [java.util.concurrent CountDownLatch]])


;;; The basic idea is to spawn a few Java Threads and attempt to modify the message
;; ref concurrently using the add-message-commute and observe the output.
;; The problem is that I failed to create a method that can update the messages
;; ref in a separate thread of execution

(def messages (ref ()))
(defrecord Message [sender text])

;; Use commute when you do not care about the order in which the updates are applyed to the ref
(defn add-message-commute [msg, messages]
  (dosync (commute messages conj msg)))
(add-message-commute (Message. "Alin" "Hello") messages)


;; This is how you start a thread of execution in Clojure
(.start (Thread. (fn [] (println "This was printed in a seprate thread"))))

(defn add [sender-name, no-of-messages, list]
  (do
    (println "Adding some messages to the ref")
    (map #(add-message-commute (Message. sender-name %) list) (reverse (range 0 no-of-messages)))))

(add "blah" 5 messages)

;; This will start a thread
(defn start-thread [run-method]
  (.start (Thread. run-method)))
(start-thread #(println "thread name: " (.getName (java.lang.Thread/currentThread))))

;; For some reason this function does not work, i.e. it does not add messages to the ref.
(defn add-messages-on-thread [no-of-msg-per-thread thread-name list]
    (start-thread #(add thread-name no-of-msg-per-thread list)))

(add-messages-on-thread 5 "thread-1" messages)

(defn add-messages-on-thread-with_latches [no-of-msg-per-thread, startLatch, endLatch, thread-name, list]
  (do
    (.await startLatch)
    (add-messages-on-thread no-of-msg-per-thread "thread-1" messages)
    (.countDown endLatch)))


(defn run-with-latches [list] 
  (let [startLatch (CountDownLatch. 1)
          endLatch (CountDownLatch. 1)]
        (do 
          (start-thread #(add-messages-on-thread-with_latches 5 startLatch endLatch "thread0" list))
          (.countDown startLatch)
          (.await endLatch)
          list)
        )
  )
(run-with-latches messages)


(defn create-thread-names [no-of-threads]
  (map #(str "thread" %) (range 0 no-of-threads)))
(create-thread-names 5)

;(defn run-commute-in [no-of-threads no-of-msg-per-thread, list]
;  (let [startLatch (CountDownLatch. 1)
;        endLatch (CountDownLatch. no-of-threads)]
;    (do 
;      (map 
;        #(start-thread (fn [] (run [no-of-msg-per-thread, startLatch, endLatch, %])))
;        (create-thread-names no-of-threads))
;      (.countDown startLatch)
;      (.await endLatch))
;    )
;  )

;(run-commute-in 5 5)
@messages

