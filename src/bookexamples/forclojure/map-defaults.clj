(ns bookexamples.forclojure.map-defaults)

(defn map-defaults [value m-keys]
	(into {} (map #(vector %1 value) m-keys)))

;; Interestingly enough the following will not work
;(into {} (map #(list %1 value) m-keys)))

(comment


(load-file "src/bookexamples/forclojure/map-defaults.clj")
(refer 'bookexamples.forclojure.map-defaults)

(map-defaults :a '(1 2 3 4 5))

)