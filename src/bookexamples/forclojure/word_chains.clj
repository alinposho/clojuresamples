(ns bookexamples.forclojure.word-chains)

;;; Problem 82: Word chains
;;A word chain consists of a set of words ordered so that each word differs by only one letter from the words directly before and after it. The one letter difference can be either an insertion, a deletion, or a substitution. Here is an example word chain:
;;
;;cat -> cot -> coat -> oat -> hat -> hot -> hog -> dog
;;
;;Write a function which takes a sequence of words, and returns true if they can be arranged into one continous word chain, and false if they cannot.
(defn word-chains? [words]
  (letfn [(lev [lev-memo x1 x2]
               (cond
                 (= 0 (count x1)) (count x2)
                 (= 0 (count x2)) (count x1)
                 true (apply min (remove nil? [(inc (lev-memo lev-memo (rest x1) (rest x2)))
                                               (inc (lev-memo lev-memo x1 (rest x2)))
                                               (inc (lev-memo lev-memo (rest x1) x2))
                                               (when (= (first x1) (first x2)) (lev-memo lev-memo (rest x1) (rest x2)))]))))
          (memo-lev [x1 x2] ((partial lev (memoize lev)) x1 x2))
          (wc [wc-memo words chain]
               (if (empty? words)
                 (every? #(= 1 (apply memo-lev %)) (partition 2 1 chain))
                 (boolean (some (fn [word] (wc-memo wc-memo (disj words word) (conj chain word))) words))))
          (memo-wc [words] ((partial wc (memoize wc)) words []))]
    (memo-wc words)))


(comment

  (= true (word-chains? #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))
  (= false (word-chains? #{"cot" "hot" "bat" "fat"}))
  (= false (word-chains? #{"to" "top" "stop" "tops" "toss"}))
  (= true (word-chains? #{"spout" "do" "pot" "pout" "spot" "dot"}))
  (= true (word-chains? #{"share" "hares" "shares" "hare" "are"}))
  (= false (word-chains? #{"share" "hares" "hare" "are"}))

  ;; Some of the solutions on the web
  (letfn [
          (dis [x1 x2] (cond
                         (= 0 (count x1)) (count x2)
                         (= 0 (count x2)) (count x1)
                         true (apply min (remove nil? [
                                                       (inc (dis (rest x1) (rest x2)))
                                                       (inc (dis x1 (rest x2)))
                                                       (inc (dis (rest x1) x2))
                                                       (when (= (first x1) (first x2)) (dis (rest x1) (rest x2)))]))))
          (begin-chain [x] (cond
                             (= 1 (count x)) [(first x)]
                             true (for [i x :when (seq (filter #(> 2 (dis i %)) (begin-chain (disj x i))))] i)))
          (is-chain [x] (boolean (seq (begin-chain x))))] is-chain)


  )

