(ns bookexamples.forclojure.palindromic-numbers)

;; Problem 150:
;; A palindromic number is a number that is the same when written forwards or backwards (e.g., 3, 99, 14341).
;; Write a function which takes an integer n, as its only argument, and returns an increasing lazy sequence of all
;; palindromic numbers that are not less than n.
;; The most simple solution will exceed the time limit!
(defn palindroms [n]
  (letfn [(to-number [rs]
            (loop [res (last rs)
                   pow 10N
                   r (rest (reverse rs))]
              (if (empty? r) res
                (recur (+ res (* pow (first r))) (* 10 pow) (rest r)))))

          (to-base [n base]
            (loop [xs (list (mod n base))
                    n (quot n base)]
              (if (zero? n) xs
                (recur (cons (mod n base) xs) (quot n base)))))

          (palindrom? [n]
            (= (reverse (to-base n 10)) (to-base n 10)))

          (next-palindrome [n]
            (let [s (to-base n 10N)
                  first-half (drop (quot (count s) 2) (reverse s))
                  nxt (+ 1N (to-number (reverse first-half)))
                  [h & t :as all] (reverse (to-base nxt 10))
                  new-first-half (if (< (count first-half) (count all)) t all)
                  new-n (to-number
                          (if (odd? (count s))
                            (concat (reverse first-half) (rest first-half))
                            (concat (reverse first-half) first-half)))
                  canddt-palindrome (to-number
                                      (if (odd? (count s))
                                        (concat (reverse t) new-first-half)
                                        (concat (reverse all) new-first-half)))]
              (if (> new-n n)
                new-n
                canddt-palindrome)))]
  (if (palindrom? n)
    (iterate next-palindrome n)
    (rest (iterate next-palindrome n)))))

(comment

(load-file "src/bookexamples/forclojure/palindromic-numbers.clj")
(refer 'bookexamples.forclojure.palindromic-numbers)
(take 4 (palindroms 0))
(take 1 (palindroms 1234550000))

(first (palindroms (* 11111111 111111111)))

(to-base 12345678987654321 10)

;; Some of the solutions on the web

(fn palindromic-num [num]
  (letfn [(reverse-digit [num result]
            (if
              (zero? (quot num 10))
              (+ (mod num 10) (* result 10))
              (reverse-digit (quot num 10) (+ (mod num 10) (* result 10)))))
          (palindromic-in-i-digit [low up]
            (lazy-cat
              (lazy-cat
                (map #(if (< % 10) % (reverse-digit (quot % 10) %)) (range low up))
                (drop-while
                  zero?
                  (map #(reverse-digit % %) (range low up))))
              (palindromic-in-i-digit up (* up 10))))]
    (filter #(>= % num) (let [len (count (.toString num))
                              digit (quot len 2)
                              low (quot num (apply * (repeat digit 10N)))
                              up (apply * (repeat (count (.toString low)) 10N))]
                          (palindromic-in-i-digit low up)))))

(fn iter-palindromic [x]
    (letfn [(next-palindromic [x]
                (let [xs (str x)
                      h1 (.substring xs 0 (quot (inc (count xs)) 2))
                      h2 (fn [h1] (reverse (.substring h1 0 (- (count xs) (count h1)))))
                      y (fn [h1] (read-string (apply str h1 (h2 h1))))
                      y0 (y h1)]
                    (cond
                        (< x y0) y0
                        (re-matches #"9+" xs) (+ 2 x)
                        true (y (str (inc (read-string h1)))))))]
        (if (zero? x)
            (iterate next-palindromic 0)
            (iterate next-palindromic (next-palindromic (dec x))))))

)