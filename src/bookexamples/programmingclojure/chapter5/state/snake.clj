(ns bookexamples.programmingclojure.chapter5.state.snake
  (:import (java.awt Color Dimension)
           (javax.swing JPanel JFrame Timer JOptionPane)
           (java.awt.event ActionListener KeyListener KeyEvent))
  (:require clojure.contrib.import-static))

;(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN)
(def VK_LEFT KeyEvent/VK_LEFT)
(def VK_RIGHT KeyEvent/VK_RIGHT)
(def VK_UP KeyEvent/VK_UP)
(def VK_DOWN KeyEvent/VK_DOWN)

(def width 75)
(def height 50)
(def point-size 10)
(def turn-millis 75)
(def win-lenght 5)
(def dirs { VK_LEFT  [-1 0]
            VK_RIGHT [1 0]
            VK_UP    [0 -1]
            VK_DOWN  [0 1] })


(defn add-points [& pts]
  (vec (apply map + pts)))

(defn multiply-points [& pts]
  (vec (apply map * pts)))

;; This will not compile since there is not list in the argument for +
;(apply + 1 2 3)

(add-points [1 2 3 4] [1 2 3 4] [1 2 3 4])

(multiply-points [1 2 3 4] [1 2 3 4] [1 2 3 4])

(map + [1 2] [1 2])

;; This returns a vector of vectors
(vector [:a :b] [:c :d])
;; This returns a list of vectors
(map vector [:a :b] [:c :d])

(defn point-to-screen-rect [pt]
  (map #(* point-size %)
     [(pt 0) (pt 1) 1 1]))

(point-to-screen-rect [5 10])

;; Interesting that vectors are functions, similar to Scala
([5 10] 1)
([5 10] 0)

(defn create-apple []
  {:location [(rand-int width) (rand-int height)]
  :color (Color. 210 50 90)
  :type :apple})

;; Check whether the new function works
(create-apple)
;; Now print nicely the location of the apple
(->> (create-apple) :location (print "Apple location is "))

(defn create-snake []
  {:body (list [1 1])
   :dir [1 0]
   :type :snake
   :color (Color. 15 160 70)})

(create-snake)

;; Read the explanation in the book in order to gain a better understanding of this function
;; Basically the first line decomposes the snake mape, and binds only the
(defn move [{:keys [body dir] :as snake} & grow]
  (assoc snake :body (cons (add-points (first body) dir)
                           (if grow body (butlast body)))))


(move (create-snake))
;-> {:body ([2 1]), ; etc.
(-> (create-snake) move :body)

(move (create-snake) :grow)
;-> {:body ([2 1] [1 1]), ; etc.


(defn decompose [{:keys [body type] :as snake}]
  (vector body type))

(decompose (create-snake))

(defn win? [{body :body}] 
  "A function to test whether we won the game"
  (>= (count body) win-lenght))

(win? (create-snake))

(let [win-lenght 1 ]
  (win? (create-snake)))

(win? {:body [[1 1] [4 5] [6 7] [8 9] [6 7]]})

(defn head-overlaps-body? [{[head & body] :body}]
  (contains? (set body) head))

(head-overlaps-body? {:body [[1 1] [4 5] [6 7] [8 9] [6 7]]})
(head-overlaps-body? {:body [[1 1] [4 5] [6 7] [8 9] [1 1s]]})

(def lose? head-overlaps-body?)

(defn eats? [{[snake-head] :body} {apple :location}])












