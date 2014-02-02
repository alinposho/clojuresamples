(ns bookexamples.programmingclojure.chapter2.exploringclojure.functions)

;; Of course only the last argument can be a variable length list argument 
(defn date [person-1 person-2 & chaperones]
  (println person-1 "and" person-2 "went out with" (count chaperones) "chaperones."))

(date "Rome" "Juliet" "Foo" "blah" "blah 4")

(require '[clojure.string :as str])
(defn indexable-word? [word]
  (> (count word) 2))

(filter indexable-word? (str/split "A fine day it is" #"\W+"))

(count "blah")
(count [1 2 3])

;; The same function defined using annonimous functions
(filter (fn [w] (> (count w) 2)) (str/split "A fine day it is" #"\W+"))
;; Even shorter sintax
(filter #(> (count %) 2) (str/split "A fine day it is" #"\W+"))

;; Generate functions dinamically, during the runtime
(defn make-greeter [greeter-prefix]
  (fn [username] 
    (str greeter-prefix ", " username)))

(def hello-greeter (make-greeter "Hello"))
(hello-greeter "Alin")

((make-greeter "Aloha") "Alin")