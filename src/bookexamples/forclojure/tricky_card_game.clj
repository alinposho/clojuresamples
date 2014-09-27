(ns bookexamples.forclojure.tricky-card-game)


;; Problem 141: In trick-taking card games such as bridge, spades, or hearts, cards are played in groups known as "tricks" - each player plays a single card, in order; the first player is said to "lead" to the trick. After all players have played, one card is said to have "won" the trick. How the winner is determined will vary by game, but generally the winner is the highest card played in the suit that was led. Sometimes (again varying by game), a particular suit will be designated "trump", meaning that its cards are more powerful than any others: if there is a trump suit, and any trumps are played, then the highest trump wins regardless of what was led.
;;              Your goal is to devise a function that can determine which of a number of cards has won a trick. You should accept a trump suit, and return a function winner. Winner will be called on a sequence of cards, and should return the one which wins the trick. Cards will be represented in the format returned by Problem 128, Recognize Playing Cards: a hash-map of :suit and a numeric :rank. Cards with a larger rank are stronger.

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