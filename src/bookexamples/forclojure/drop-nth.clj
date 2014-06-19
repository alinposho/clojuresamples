(ns bookexamples.forclojure.drop-nth)

(defn drop-nth [n coll] 
	(keep-indexed #(when-not (zero? (mod (inc %1) n)) %2) coll))

(drop-nth 3 [1 2 3 4 5 6])