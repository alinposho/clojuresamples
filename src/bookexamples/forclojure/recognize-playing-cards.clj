(ns bookexamples.forclojure.recognize-playing-cards)

(defn cards [code] 
	(let [card-values (zipmap (concat (apply str (range 2 10)) "TJQKA") (range 13)) 
		  suits (zipmap "SHDC" [:spade :heart :diamond :club])]
		  (zipmap [:suit :rank][(suits (first code)) (card-values (second code))])))

(comment

(load-file "src/bookexamples/forclojure/recognize-playing-cards.clj")
(refer 'bookexamples.forclojure.recognize-playing-cards)

(= {:suit :diamond :rank 10} (cards "DQ"))
(= {:suit :heart :rank 3} (cards "H5"))
(= {:suit :club :rank 12} (cards "CA"))
(= (range 13) (map (comp :rank cards str)
                   '[S2 S3 S4 S5 S6 S7
                     S8 S9 ST SJ SQ SK SA]))


;; Some of the solutions on the web
(#(fn [[a b]]
  {:suit (% a) :rank (if (% b) (% b) (- (int b) 50))}
  )
(zipmap "DHCTJQKA" [:diamond :heart :club 8 9 10 11 12]))

(fn [[s r]]
  {:suit ({\D :diamond \H :heart \S :spade \C :club} s)
  :rank ((zipmap "23456789TJQKA" (range)) r)}

)