(ns the-joy-of-clojure.chapter7-common)

(defn neighbors
	"Returns the positions of the neighbors of a position yx from
	a 2D matris of the size of size"
	([size yx] (neighbors [[-1 0] [1 0] [0 -1] [0 1]] size yx))
	([deltas size yx]
		(filter (fn [new-yx] (every? #(< -1 % size) new-yx))
				(map #(map + yx %) deltas))))

(neighbors 3 [1 1])

(def matrix
		[[1 2 3]
		[4 5 6]
		[7 8 9]])

; (get-in matrix [1 1])

; (map #(get-in matrix %) (neighbors 3 [1 1]))
; (neighbors [[-2 0] [2 0] [0 -2] [0 2]] 5 [2, 2])
