(ns the-joy-of-clojure.referential-tranparency)

(def plays )

(def plays [{:band "Burial", :plays 979, :loved 9}
		    {:band "Eno", :plays 2333, :loved 15}
		    {:band "Bill Evans", :plays 979, :loved 9}
		    {:band "Magma", :plays 2665, :loved 31}])

(defn keys-apply [f ks m]
	(let [only-keys (select-keys m ks)]
		(zipmap (keys only-keys) (map f (vals only-keys)))))


(keys-apply #(.toUpperCase %) #{:band} (plays 0))
