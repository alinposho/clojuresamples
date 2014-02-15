(ns bookexamples.programmingclojure.chapter5.state.snake
  (:use (bookexamples.programmingclojure.chapter5.state.snake )))

;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Now for the mutable STP part of the snake program

(defn reset-game [snake apple]
  (dosync 
    (ref-set snake (create-snake))
    (ref-set apple (create-apple)))
  nil)

(def snake (ref nil))
(def apple (ref nil))

(reset-game snake apple)
@snake
@apple

(defn update-direction [snake newdir]
  (when newdir (dosync (alter snake turn newdir))))

(update-direction snake (dirs VK_UP))

(defn update-position [snake apple]
  "If the snake eats the apple the snake grows, otherwise, the snake simply moves"
  (dosync 
    (if (eats? @snake @apple) 
      (do
        (alter snake move :grow)
        (ref-set apple (create-apple)))
      (alter snake move))))

(update-position snake apple)
@snake
@apple