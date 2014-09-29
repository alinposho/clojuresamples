(ns bookexamples.forclojure.tic-tac-toe)

;; Problem 73:
;;A tic-tac-toe board is represented by a two dimensional vector. X is represented by :x, O is represented by :o, and empty is represented by :e. A player wins by placing three Xs or three Os in a horizontal, vertical, or diagonal row. Write a function which analyzes a tic-tac-toe board and returns :x if X has won, :o if O has won, and nil if neither player has won.
(defn analyze-ttt [board]
  (letfn [(columns [board]
            (partition (count (first board)) (apply interleave board)))
          (not-all-empty? [xs] (not-every? #{:e} xs))
          (diag1 [board]
            (loop [i 0 res []]
              (if (>= i (count board))
                res
                (recur (inc i) (conj res ((board i) i))))))
          (diag2 [board]
            (loop [i 0 res []]
              (if (>= i (count board))
                res
                (recur (inc i) (conj res ((board i) (- (count board) i 1)))))))]
    (ffirst
      (filter #(apply = %)
               (filter not-all-empty?
                       (concat board (columns board) [(diag1 board) (diag2 board)]))))))


(comment

(load-file "src/bookexamples/forclojure/tic_tac_toe.clj")
(refer 'bookexamples.forclojure.analyze-ttt)


;; Some of the solutions on the web
(fn [board]
  (letfn [(check-horizonal [board]
            (first (flatten (filter #(every? #{:x :o } %) (filter #(= 1 (count (set %))) board)))))]
    (let [h (check-horizonal board)]
      (if
        h
        h
        (let [v (check-horizonal (apply (partial map #(vec [%1 %2 %3])) board))]
          (if
            v
            v
            (let [fd (check-horizonal [[(first (first board)) (second (second board)) (last (last board))]])]
              (if
                fd
                fd
                (check-horizonal [[(last (first board)) (second (second board)) (first (last board))]])))))))))

(fn [x] (ffirst (filter #(and (apply = %) (not= (first %) :e)) (partition 3 (map (vec (flatten x)) '(0 1 2 3 4 5 6 7 8 0 3 6 1 4 7 2 5 8 0 4 8 2 4 6))))))

)
