(ns bookexamples.forclojure.game-of-life)

;;; The game of life is a cellular automaton devised by mathematician John Conway.
;;; The 'board' consists of both live (#) and dead ( ) cells. Each cell interacts with its eight neighbours (horizontal, vertical, diagonal), and its next state is dependent on the following rules:
;;; 2) Any live cell with two or three live neighbours lives on to the next generation.
;;; 3) Any live cell with more than three live neighbours dies, as if by overcrowding.
;;; 4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
;;; Write a function that accepts a board, and returns a board representing the next generation of cells.

(defn next-generation[board]
  board)

(comment

  (load-file "src/bookexamples/forclojure/game_of_life.clj")
  (refer 'bookexamples.forclojure.game-of-life)


  ;; Some of the solutions on the web

  )