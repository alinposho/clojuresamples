(ns the-joy-of-clojure.clojure-refs
	[:use [the-joy-of-clojure.chapter7-common]])

(io! (.println System/out "Haikeeba!"))

(def initial-board
	[[:- :k :-]
	 [:- :- :-]
	 [:- :K :-]])

(defn board-map 
	"Applies the function to each element of the matrix represented as a vector of vectors"
	[f bd]
	(vec (map #(vec (for [s %] (f s))) bd)))

;; Create a chessboard of refs
; (board-map ref initial-board)

(defn reset!
	"Resets the board state "
	[]
	(def board (board-map ref initial-board))
	(def to-move (ref [[:K [2 1]] [:k [0 1]]]))
	(def num-moves (ref 0)))

(def king-moves (partial neighbors [[-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]] 3))

(defn good-move? [to enemy-sq]
	(when (not= to enemy-sq) to))

(defn choose-move [[[mover mpos][_ enemy-pos]]]
	[mover (some #(good-move? % enemy-pos) (shuffle (king-moves mpos)))])


;; Now let's take the functions for a spin
(reset!)
(take 5 (repeatedly #(choose-move @to-move)))
