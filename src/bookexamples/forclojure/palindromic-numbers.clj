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
                  cnddt-palindrome (to-number
                                      (if (odd? (count s))
                                        (concat (reverse t) new-first-half)
                                        (concat (reverse all) new-first-half)))]
              (if (> new-n n)
                new-n
                cnddt-palindrome)))]
  (if (palindrom? n)
    (iterate next-palindrome n)
    (rest (iterate next-palindrome n)))))
(take 4 (palindroms 0))

(take 1 (palindroms 1234550000))

 (first (palindroms (* 11111111 111111111)))
(* 11111111 111111111)
(* 111111111 111111111)

(to-base 12345678987654321 10)

(comment

(load-file "src/bookexamples/forclojure/palindromic-numbers.clj")
(refer 'bookexamples.forclojure.palindromic-numbers)


;; Some of the solutions on the web

)
