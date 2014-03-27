(ns the_joy_of_clojure.symbolic-resolution)

;; symbols are not based on name alone
(identical? 'goat 'goat)
;; But equals is trus
(= 'goat 'goat)

(name 'goat)

(def goat "blah")
(symbol "goat")
(quote goat)

;; Symbols can have metadata attached 
(let [x (with-meta 'goat {:ornery true})
	  y (with-meta 'goat {:ornery false})]
	  [(identical? x y)
	   (= x y)
	   (meta x)
	   (meta y)
	  ])

(resolve 'goat)
;;'a-symbol

;; Clojure is a Lisp-1 which means that variables and functions have the same name resolution
;; We can define a function
(defn hello [] 
	"blah")
(hello)

;; And replace tje binding to "blah" with a value
(def blah 1)

;; Because Clojure is a Lips-1 we can use the f parameter in the function(operation) position without needing a special
;; construct
(defn best [f xs]
	(reduce #(if (f %1 %2) %1 %2) xs))

(best > [9 1 6 10 3 6 1])

