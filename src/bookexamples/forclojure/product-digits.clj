(ns bookexamples.forclojure.product-digits)

(defn product-digits
	([a b] (product-digits (* a b)))
	([n]
	(if (zero? (quot n 10)) 
		[(mod n 10)]
		(conj (product-digits (quot n 10)) (mod n 10)))))

(comment

(load-file "src/bookexamples/forclojure/product-digits.clj")
(refer 'bookexamples.forclojure.product-digits)

(product-digits 999 99)

;; Some of the solutions on the web
(fn [x y]
  (->> (* x y)
     str
     (map str)
     (map #(Integer/parseInt %))))

(fn split-product [x y]
  (vec (map #(Character/getNumericValue %)(str (* x y)))))

(comp (partial map #(- (int %) 48)) str *)

)