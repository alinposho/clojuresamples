(ns bookexamples.forclojure.word-chains)

;;; Problem 82: Word chains
;;A word chain consists of a set of words ordered so that each word differs by only one letter from the words directly before and after it. The one letter difference can be either an insertion, a deletion, or a substitution. Here is an example word chain:
;;
;;cat -> cot -> coat -> oat -> hat -> hot -> hog -> dog
;;
;;Write a function which takes a sequence of words, and returns true if they can be arranged into one continous word chain, and false if they cannot.

(def levenshtein
  (memoize
    (fn [xs ys]
      (cond (zero? (count xs)) (count ys)
            (zero? (count ys)) (count xs)
            :else (min
                    (inc (levenshtein (rest xs) ys))
                    (inc (levenshtein xs (rest ys)))
                    (+ (levenshtein (rest xs) (rest ys))
                       (if (= (first xs) (first ys)) 0 1)))))))

(def word-chains?
  (memoize
    (fn
      ([words] (true? (word-chains? words [])))
      ([words chain]
       (if (empty? words)
         (every? #(= 1 (apply levenshtein %)) (partition 2 1 chain))
         (some (fn [word] (word-chains? (disj words word) (conj chain word))) words))))))

(letfn [(dis [dis-memoized x1 x2]
             (cond
               (= 0 (count x1)) (count x2)
               (= 0 (count x2)) (count x1)
               true (apply min (remove nil? [(inc (dis-memoized dis-memoized (rest x1) (rest x2)))
                                             (inc (dis-memoized dis-memoized x1 (rest x2)))
                                             (inc (dis-memoized dis-memoized (rest x1) x2))
                                             (when (= (first x1) (first x2)) (dis-memoized dis-memoized (rest x1) (rest x2)))]))))]
  (partial dis (memoize dis)))

(defn word-chains? [words]
  (letfn [(lev [lev-memoized x1 x2]
               (cond
                 (= 0 (count x1)) (count x2)
                 (= 0 (count x2)) (count x1)
                 true (apply min (remove nil? [(inc (lev-memoized lev-memoized (rest x1) (rest x2)))
                                               (inc (lev-memoized lev-memoized x1 (rest x2)))
                                               (inc (lev-memoized lev-memoized (rest x1) x2))
                                               (when (= (first x1) (first x2)) (lev-memoized lev-memoized (rest x1) (rest x2)))]))))
          (memo-lev [x1 x2] ((partial lev (memoize lev)) x1 x2))
          (wc ([wc-memo words] (true? (wc-memo wc-memo words [])))
              ([wc-memo words chain]
               (if (empty? words)
                 (every? #(= 1 (apply memo-lev %)) (partition 2 1 chain))
                 (some (fn [word] (wc-memo wc-memo (disj words word) (conj chain word))) words))))
          (memo-wc [words] ((partial wc (memoize wc)) words))]
    (memo-wc words)))


(comment


  ;; Some of the solutions on the web
  (= true (word-chains? #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))
  (= false (word-chains? #{"cot" "hot" "bat" "fat"}))
  (= false (word-chains? #{"to" "top" "stop" "tops" "toss"}))
  (= true (word-chains? #{"spout" "do" "pot" "pout" "spot" "dot"}))
  (= true (word-chains? #{"share" "hares" "shares" "hare" "are"}))
  (= false (word-chains? #{"share" "hares" "hare" "are"}))

  )

