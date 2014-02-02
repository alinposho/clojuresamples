(ns bookexamples.programmingclojure.chapter2.exploringclojure.reader-macros)

(use bookexamples.programmingclojure.chapter2.exploringclojure.reader-macros)

;; The "'" character will tell the reader not to evaluate the list 
'(1 2)
;; This is the same as
(quote (1 2))

(defn greeting
"Returns a greeting of the form 'Hello, username.'"
  [username]
  (str "Hello, " username))

(greeting "Alin")
(doc greeting)

;; You can define a function of zero arguments that calls the one with
;; one argument
(defn greeting
"Returns a greeting of the form 'Hello, username.'
Default username is 'world'."
  ([] (greeting "world"))
  ([username] (str "Hello, " username)))

(greeting)



















