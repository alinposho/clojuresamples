(ns bookexamples.forclojure.cartesian-product)

(defn cartesian-product [xs ys]
	(set (for [x xs y ys] [x y])))

(comment

(load-file "src/bookexamples/forclojure/cartesian-product.clj")
(refer 'bookexamples.forclojure.cartesian-product)

(cartesian-product #{1 2 3} #{4 5})
(= (cartesian-product #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
   #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
     ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
     ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})
(= (cartesian-product #{1 2 3} #{4 5})
   #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})
(= 300 (count (cartesian-product (into #{} (range 10))
                  (into #{} (range 30)))))

;; Some of the solutions on the web

)