(ns bookexamples.forclojure.my-merge-with)

(defn my-merge-with
  ([f xs ys]
    (reduce (fn [acc pair] 
          (let [k (key pair)
              vlatter (val pair)
              vres (acc k)]
            (assoc acc k (if (nil? vres) vlatter (f vres vlatter)))))
      xs ys))
  ([f xs ys & zrest] (reduce #(my-merge-with f %1 %2) (my-merge-with f xs ys) zrest)))

(comment

(load-file "src/bookexamples/forclojure/my-merge-with.clj")
(refer 'bookexamples.forclojure.my-merge-with)

(= (my-merge-with * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
   {:a 4, :b 6, :c 20})
(= (my-merge-with - {1 10, 2 20} {1 3, 2 10, 3 15})
   {1 7, 2 10, 3 15})
(= (my-merge-with concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
   {:a [3 4 5], :b [6 7], :c [8 9]})

;; Some of the solutions on the web
(fn [f & args]
  (reduce (fn[map1 map2]
            (reduce (fn [m [k v]]
                      (if-let [vv (m k)]
                        (assoc m k (f vv v))
                        (assoc m k v)))
                    map1 map2))
          args))


(fn [f & x] 
  ((comp #(zipmap (keys %) (map (comp (partial reduce f) (partial map last)) (vals %))) 
    (partial group-by first) (partial apply concat)) x))



)