(ns bookexamples.programmingclojure.chapter2.exploringclojure.clojure-form)

(concat [1 2] [3 4])

;; Strings are multiline
"This is a multiline
        string"

;; Most of java.lang.String methods can be called directly no wrapping needed
(.toUpperCase "blah")


;; instead of toString() use str which skips nils altogether.
(str 1 2 nil 3 )

(str \h \e \y \space \y \o \u)

;; This results in a list
(interleave "Attack at midnight" "The purple elephant chortled")

;; This does not produce a String at the output
(str (interleave "Attack at midnight" "The purple elephant chortled"))

;; But this will 
(apply str (interleave "Attack at midnight" "The purple elephant chortled"))
;; This will reveal the message "encrypted" by the call above
(apply str (take-nth 2 "ATthtea cpku raptl em iedlneipghhatn"))

;; The empty list evaluates to true
(if () "We are in Clojure!" "We are in Common Lisp!")
(if 0 "Zero is true" "Zero is fase")

(zero? 0.0)

(zero? (/ 22 7))

(def inventors {"Lisp" "McCarthy" "Clojure" "Hickey"})

(inventors "Lisp")

(inventors "foo")

(get inventors "Clojure")

(get inventors "foo" :default)

;; keywords
:foo

;; Will not compile since it is not boud to anything
foo

("Lisp" inventors)

(def inventors {:List "McCarthy" :Clojure "Hickey"})
;; because we used keywords we can do the following
(:List inventors)


(defrecord Book [title author])

(->Book "title" "author")

(def b (->Book "Anathem" "Neal Stephenson"))
b
;; The Book structure behaves almost like a map
(:title b)
(def b (Book. "Programming Clojure" "Stuart Halloway"))

(refer 'bookexamples.programmingclojure.chapter2.exploringclojure.clojure-form)
#bookexamples.programmingclojure.chapter2.exploringclojure.clojure-form.Book{:title "Infinite Jest", :author "David Foster Wallace"}

















