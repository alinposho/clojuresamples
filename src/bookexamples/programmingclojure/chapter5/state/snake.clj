(ns bookexamples.programmingclojure.chapter5.state.snake
  (:import (java.awt Color Dimension)
           (javax.swing JPanel JFrame Timer JOptionPane)
           (java.awt.event ActionListener KeyListener))
  (:require clojure.contrib.import-static))

;(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN)

(defn add-points [& pts]
  (vec (apply map + pts)))


(add-points [1 2 3 4] [1 2 3 4] [1 2 3 4])

(apply + [1 2] [1 2])

(map + [1 2] [1 2])

