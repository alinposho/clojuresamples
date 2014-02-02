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







