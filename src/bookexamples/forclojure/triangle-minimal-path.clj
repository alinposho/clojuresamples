(ns bookexamples.forclojure.triangle-minimal-path)

(defn sum-min-path [tgl]
  (letfn [(min-path 
            ([[x1 x2 & xs]]
              (if (nil? x2) x1
                (recur (cons (compute-new-vector x1 x2) xs)))))
          (compute-new-vector [xn xn-1]
            (vec 
              (map-indexed 
                (fn [idx itm] (apply min (map (comp (partial + itm) xn) (vector idx (inc idx)))))
                  xn-1)))]
  (first (min-path (reverse tgl)))))

(defn new-last [lst blst] 
  (map-indexed 
    (fn [idx itm] (apply min (map (comp (partial + itm) lst) (vector idx (inc idx)))))
      blst))

(new-last [7 8 9 10] [4 5 6])

(comment

(load-file "src/bookexamples/forclojure/triangle-minimal-path.clj")
(refer 'bookexamples.forclojure.triangle-minimal-path)

(sum-min-path [1])

(sum-min-path '([1]
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