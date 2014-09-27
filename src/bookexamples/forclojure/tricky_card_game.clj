(ns bookexamples.forclojure.tricky-card-game)

(defn tricky-card-game [trump]
	(fn [cards]
		(let [lead-game (if trump trump (:suit (first cards)))]
			(last 
				(sort-by :rank (filter #(= (:suit %) lead-game) cards))))))

(comment

(load-file "src/bookexamples/forclojure/tricky_card_game.clj")
(refer 'bookexamples.forclojure.tricky-card-game)


;; Some of the solutions on the web
(fn [trump]
  (fn [trick]
    (let [lead-suit (if trump trump (:suit (first trick)))]
      (last (sort-by :rank (filter #(= lead-suit (:suit %)) trick))))))

partial
(fn tricky [trump cards]
    (let [suitsort (merge {:heart 2 :diamond 2 :spade 2 :club 2} {((first cards) :suit ) 1} {trump 0})
          sortfn (fn [x] [(suitsort (x :suit )) (- (x :rank ))])]
        (first (sort-by sortfn cards))))

)