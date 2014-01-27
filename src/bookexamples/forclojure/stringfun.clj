(ns bookexamples.forclojure.stringfun)

(str "Alin " "Posho")

(#(str "Hello, " % "!") "Dave")

(defn hello [x] 
  (str "Hello, " x "!"))

(hello "Alin")
(= (str nil) "")
(apply str [1 2 3])
(= (str [1 2 3]) "[1 2 3]")