(ns bookexamples.forclojure.stringfun)

(str "Alin " "Posho")

(#(str "Hello, " % "!") "Dave")

(defn hello [x] 
  (str "Hello, " x "!"))

(hello "Alin")