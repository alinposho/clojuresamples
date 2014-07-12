(ns bookexamples.forclojure.triangle-minimal-path)

(defn min-path [tgl]
	(if (= 1 (count tgl)) 
    (first (first tgl))
    (let [rtgl (reverse tgl)
          lst (first rtgl)
          blst (second rtgl)
          nlst (vec 
                  (map-indexed 
                    (fn [idx itm] (apply min (map (comp (partial + itm) lst) (vector idx (inc idx)))))
                      blst))]
      (recur (reverse (cons nlst (drop 2 rtgl)))))))

(defn new-last [lst blst] 
  (map-indexed 
    (fn [idx itm] (apply min (map (comp (partial + itm) lst) (vector idx (inc idx)))))
      blst))

(new-last [7 8 9 10] [4 5 6])

(comment

(load-file "src/bookexamples/forclojure/triangle-minimal-path.clj")
(refer 'bookexamples.forclojure.triangle-minimal-path)

(min-path [1])

(min-path '([1]
          [2 4]
         [5 1 4]
        [2 3 4 5]))

(= 20 (min-path '([3]
           [2 4]
          [1 9 3]
         [9 9 2 4]
        [4 6 6 7 8]
       [5 7 3 5 1 4])))


;; Some of the solutions on the web


)