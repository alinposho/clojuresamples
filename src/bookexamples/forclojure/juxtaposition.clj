(ns bookexamples.forclojure.juxtaposition)

(defn juxtaposition [& fs] 
	(fn [& xs]
		(map #(apply % xs) fs)))


(comment

(load-file "src/bookexamples/forclojure/juxtaposition.clj")
(refer 'bookexamples.forclojure.juxtaposition)

(= [21 6 1] ((juxtaposition + max min) 2 3 5 1 6 4))
(= ["HELLO" 5] ((juxtaposition #(.toUpperCase %) count) "hello"))
(= [2 6 4] ((juxtaposition :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))

;; Some of the solutions on the web
#(into {}
  (map (fn [[k v]] [k (count v)]) (group-by identity %)))

(comp #(zipmap (keys %) (map count (vals %))) (partial group-by identity))

)