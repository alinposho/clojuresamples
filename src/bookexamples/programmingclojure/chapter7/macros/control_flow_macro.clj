(ns bookexamples.programmingclojure.chapter7.macros.control-flow-macro)

;; First rule, try to define the macro as a function
(defn unless [expr form] 
  (if expr nil form))

;; Notice that the "form" expression gets evaluated too early, therefore, the message
;; gets printed when it shouldn't
(unless true (println "This should NOT print!"))

(unless false (println "This should print."))

(defmacro unless [expr form] 
  (list 'if expr nil form))

;; Using a macro fixed our little issue with the message being evaluated too soon.
(unless true (println "This should NOT print!"))
(unless false (println "This should print."))


(macroexpand-1 '(unless true (println "This should NOT print!")))

;; An improved version of unless that works for a variable number of arguments
(defmacro unless [expr & form]
  (list 'if expr nil (cons 'do form)))


(unless true (println "This should NOT print!") (println "This should NOT print as well!"))
(unless false (println "This should print!") (println "This should print as well!"))


(defmacro unless [expr & form]
  (list 'if expr nil (conj  form 'do)))

(unless true (println "This should NOT print!") (println "This should NOT print as well!"))
(unless false (println "This should print!") (println "This should print as well!"))


