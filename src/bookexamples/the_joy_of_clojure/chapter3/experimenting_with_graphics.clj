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