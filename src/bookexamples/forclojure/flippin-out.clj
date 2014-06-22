(ns bookexamples.forclojure.flippin-out)

(defn flippin-out [f]
	#(f %2 %1))

(comment

(load-file "src/bookexamples/forclojure/flippin-out.clj")
(refer 'bookexamples.forclojure.flippin-out)

(= (flippin-out -4 '(:a :b :c)) '(:c :a :b))

((flippin-out nth) 2 [1 2 3 4 5])

;; Some of the solutions on the web
(def f1 #(comp (partial apply %) reverse list))
((f1 nth) 2 [1 2 3 4 5])

)