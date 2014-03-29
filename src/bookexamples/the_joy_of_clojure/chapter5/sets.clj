(ns the-joy-of-clojure.sets)

;; The following two statements will raise exceptions because we are attempting to create sets with duplicate keys
; #{[] ()}
; #{[1 2] [1 2]}

(some #{:b} [:a 1 :b 2])
(some #{:b 1} [:a 1 :b 2])
