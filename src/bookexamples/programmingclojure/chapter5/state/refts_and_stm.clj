(ns bookexamples.chapter5.state.refts-and-stm
  [:import [java.util.concurrent CountDownLatch]])

(def current-track (ref "Mars, the Bringer of War"))

@current-track
(deref current-track)

;; This will cause an exception since it is not done in a transaction
;(ref-set current-track "Rachmaninoff - Piano Concerto No 2")

;; Notice that that the function dosync returns the new value assigned to the ref
(def new-current-track 
  (dosync
    (ref-set current-track "Piano Concerto No 2")))

new-current-track
@current-track

(def current-composer (ref "Rachmaninoff"))
@current-composer

;; Notice that this will return the value of the last reference set
(dosync 
  (ref-set current-track "Raindrops Prelude")
  (ref-set current-composer "Frederic Chopin"))

@current-track
@current-composer

(defrecord Message [sender text])
(def messages (ref ()))
@messages

(def msg (Message. "Alin" "Hello"))
msg
;; Bad implementation to add message

(defn naive-add-message [msg]
  (dosync 
    (ref-set messages (conj @messages msg))))

(naive-add-message msg)

@messages


(defn add-message [msg]
  (dosync (alter messages conj msg)))

(add-message (Message. "Alin" "Hello Again"))

;; Use commute when you do not care about the order in which the updates are applyed to the ref
(defn add-message-commute [msg]
  (dosync (commute messages conj msg)))
(add-message-commute (Message. "Alin" "Hello"))

(def continueLatch (CountDownLatch. 1))

;; This function will block until the latch is released
(defn add-message-commute-with-latch [msg latch]
  (future 
    (dosync 
      (commute messages 
               (fn [list msg]
                 (do 
                   (.await latch)
                   (conj list msg)))
               msg))))
  
(def f (add-message-commute-with-latch (Message. "Latch" "First Message") continueLatch))
;; Messages value will not be altered, since the future did not complete, considering that the latch is not released
@messages

(defn test-commute [] 
  (do
    (def messages (ref ()))
    (def continueLatch (CountDownLatch. 1))
    (def f (add-message-commute-with-latch (Message. "Latch" "First Message") continueLatch))
    (java.lang.Thread/sleep 100)
    (println "Messages after the latch guarded commute add: \n" @messages)
    (add-message-commute (Message. "NonBlocking" "Second Message"))
    (java.lang.Thread/sleep 100)
    (println "Messages after the non blocking commute add: \n" @messages)
    (.countDown continueLatch)
    (java.lang.Thread/sleep 100)
    (println "Messages after the latch has been released: \n" @messages)
    )
  )
(test-commute)

(.countDown continueLatch)

(defn update-on-separate-thread)


;; Adding validation to refs
(def validate-message-list 
  (partial every? #(and (:sender %) (:text %))))

(validate-message-list [{:sender "blah", :text "1"}])
(validate-message-list [{:sender "blah", :text "1"} {:sender "blah", :text "2"}])


(def messages (ref () :validator validate-message-list))
;; This value will not be added to the list and the list will be left untouched
;(add-message "kjhdjshdj")
@messages




















