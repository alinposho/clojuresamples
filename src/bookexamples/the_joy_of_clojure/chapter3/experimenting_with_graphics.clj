(ns the_joy_of_clojure.graphics)

(defn xors [max-x max-y]	
	(for [x (range max-x) 
		  y (range max-y)]
		  [x y (bit-xor x y)]))

(defn create-frame [] 
  (doto (java.awt.Frame.)
    (.setVisible true)
    (.setSize (java.awt.Dimension. 200 200))))

(defn get-gfx [frame] (.getGraphics frame))

(defn print-frame [frame] 
  (let [gfx (get-gfx frame)]
    (doseq [[x y xor] (xors 200 200)]
      (.setColor gfx (java.awt.Color. xor xor xor))
      (.fillRect gfx x y 1 1))))

;(def frame (create-frame))
;(print-frame frame)
;(.setVisible frame false)

;; Running this code will cause an exception to be raised. In order to see the full stack trace call (.printStackTrace *e)
;(def gfx (get-gfx frame))
;(doseq [[x y xor] (xors 500 500)]
;  (.setColor gfx (java.awt.Color. xor xor xor))
;  (.fillRect gfx x y 1 1))
;(.printStackTrace *e)

(defn f-values [f xs ys]
  (for [x (range xs) y (range ys)]
    [x y (rem (f x y) 256)]))

(defn clear [g] (.clearRect g 0 0 200 200))

(defn draw-values [frame f xs ys]
  (let [gfx (get-gfx frame)] 
    (.setSize frame (java.awt.Dimension. xs ys))
    (doseq [[x y v] (f-values f xs ys)]
      (.setColor gfx (java.awt.Color. v v v))
      (.fillRect gfx x y 1 1))))

;(def frame (create-frame))
;(.setVisible frame true)
;(draw-values frame bit-xor 256 256)
;(draw-values frame + 256 256)
;(draw-values frame * 1024 1024)
;(.setVisible frame false)







