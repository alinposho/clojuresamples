(ns the-joy-of-clojure.named-parameters)

;; Notice that the slope function defines default values for its :p1 and :p2 keys in case they are not 
;; passed as parameters. 
(defn slope
	[& {:keys [p1 p2] :or {p1 [0 0] p2 [1 1]}}]
	(float (/ (- (p2 1) (p1 1))
		       (- (p2 0) (p1 0)))))

;; Please note that we do not need to pass a map to the function for it to destructure the input
(slope :p1 [4 15] :p2 [3 21])

;; In fact it's illegat to pass in a map, as you can see if you execute the following code:
;(slope {:p1 [4 15] :p2 [3 21]})

;; Calling the slope function and passing one parameter
(slope :p2 [2 1])
;; and no parameters
(slope)


