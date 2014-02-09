(ns bookexamples.programmingclojure.chapter5.state.atoms)

(def current-track (atom "Venus, the Bringer of Peace"))

@current-track

(reset! current-track "Credo")

(def current-track (atom {:title "Credo" :composer "Byrd"}))

@current-track

(reset! current-track {:title "Chopin" :composer "Etudes"})

(swap! current-track assoc :title "Chopin")

