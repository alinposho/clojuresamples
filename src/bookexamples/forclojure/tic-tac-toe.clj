(ns bookexamples.forclojure.tic-tac-toe)

(defn analyze [[[a1 a2 a3 :as a]
			   [b1 b2 b3 :as b]
			   [c1 c2 c3 :as c]]]
	(if (not (apply distinct? a)) a1 
		(if  (apply not (distinct? b)) b1
			(if (not (apply distinct? c)) c1
			 	))))

((= nil (analyze [[:e :e :e]
                  [:e :e :e]
                  [:e :e :e]])))

(= :x (analyze [[:x :x :x]
	            [:x :e :e]
	            [:x :e :o]]))