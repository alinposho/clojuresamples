(ns bookexamples.chapter5.state.refts-and-stm)

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

@messages













