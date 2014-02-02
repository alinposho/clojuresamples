(ns bookexamples.programmingclojure.getting.started.examples)

(defrecord Person [first-name last-name])

(def foo (->Person "Aaron" "Brenda"))

(defn helloworld [username] 
  (println (format "Hello, %s" username)))

(helloworld "Alin")

; Clojure cond
(def x 11)
(cond (= x 10) "equal"
      (> x 10) "more")

(java.lang.String/valueOf 10)
(java.lang.System/getProperties)

; shorthand notation for chaining method call
(println "String class protectionDomain ")
(.. "hello" getClass getProtectionDomain)

; Threadsafe in memory DB
(def accounts (ref #{}))
(defrecord Account [id balance])
(dosync 
  (alter accounts conj (->Account "CLJ" 1000.00)))
@accounts

(.start (new Thread (fn [] (println "Hello" (Thread/currentThread)))))

(defn hello [name] 
  (println (str "Hello, " name)))

(hello "Alin")

(str *1 " and " *2)

(def visitors (atom #{}))

@visitors ; should print #{}

(swap! visitors conj "Stu")

(deref visitors)
@visitors ;; shorthand for deref

;; refefining hello
(defn hello 
  "Writes hello message to *out*. Calls you by username. Knows if you have been here before."
  [username]
  (swap! visitors conj username)
  (str "Hello, " username))

(hello "Stu")
(hello "Alin")

@visitors


(require 'clojure.java.io)

;; You will now be able to refer to functions defined in this namespace without 
;; needing to fully qualify their names. Similar to how imports work in Java.
(refer 'bookexamples.programmingclojure.getting.started.examples)

;; use combines require and refer in one operation
(use 'bookexamples.programmingclojure.getting.started.examples)

;; Calling doc prints the documentation for the parameter function on the REPL
(doc str)








