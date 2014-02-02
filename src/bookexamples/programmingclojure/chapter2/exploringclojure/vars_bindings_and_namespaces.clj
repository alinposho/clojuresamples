(ns bookexamples.programmingclojure.chapter2.exploringclojure.vars-bindings-and-namespaces)

(def foo 10)
foo
(var foo)
#'foo

(def number 10)
(defn triple [number] 
  (* 3 number))
;; This will print 3*5=15 not 30 
(triple 5)
number
(triple number)
#'number

;; let bingings
(defn square-corners [bottom left size]
  (let [top (+ bottom size)
        right (+ left size)]
    [[bottom left] [top left] [top right] [bottom right]]))

(square-corners 1 2 3)

;; Destructuring
(defn greet-author-1 [author]
  (println "Hello," (:first-name author)))

(greet-author-1 {:first-name "Vinge" :last-name "Vernor"})

(defn greet-author-2 [{fname :first-name}]
  (println "Hello," fname))
(greet-author-2 {:first-name "Vinge" :last-name "Vernor"})

(let [[x y] [1 2 3]]
  [x y])

(let [[x y] [1]]
  [x y])

(let [[_ _ z] [1 2 3]]
  z)
(let [[_ _ z] [1 2]]
  z)

;; _ gets bound twice, hence, the result of executing this is 2
(let [[_ _ z] [1 2 3]]
  _)

(let [[x y :as coords] [1 2 3 4 5 6]]
(str "x: " x ", y: " y ", total dimensions " (count coords)))

(resolve 'foo)
(resolve 'blahssss)

String
java.io.File/separator

(import '(java.io InputStream File))
(.exists (File. "/tmp"))








