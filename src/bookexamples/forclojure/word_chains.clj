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
      ([words] (true? (some (fn [word] (word-chains? (disj words word) [word])) words)))
      ([words chain]
       (if (empty? words)
         (every? #(= 1 (apply levenshtein %)) (partition 2 1 chain))
         (some (fn [word] (word-chains? (disj words word) (conj chain word))) words))))))


(comment


  ;; Some of the solutions on the web
  (= true (word-chains? #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))
  (= false (word-chains? #{"cot" "hot" "bat" "fat"}))
  (= false (word-chains? #{"to" "top" "stop" "tops" "toss"}))
  (= true (word-chains? #{"spout" "do" "pot" "pout" "spot" "dot"}))
  (= true (word-chains? #{"share" "hares" "shares" "hare" "are"}))
  (= false (word-chains? #{"share" "hares" "hare" "are"}))

  )

