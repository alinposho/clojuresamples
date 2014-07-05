(ns bookexamples.forclojure.pairwise-disjoint)

(defn disjoint [xs] 
	(empty? (for [x xs y xs :when (and (not= x y) (not-empty (clojure.set/intersection x y)))] [x y])))


(comment

(load-file "src/bookexamples/forclojure/pairwise-disjoint.clj")
(refer 'bookexamples.forclojure.pairwise-disjoint)

(disjoint #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}})


;; Some of the solutions on the web
(comp (partial apply distinct?)
        (partial apply concat))

#(apply distinct? (apply concat %))

)