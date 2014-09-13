(ns bookexamples.forclojure.write-roman-numerals)

;; Problem 104: This is the inverse of Problem 92, but much easier. Given an integer smaller than 4000, return the corresponding roman numeral in uppercase, adhering to the subtractive principle.
(defn write-roman-numerals [n]
  (letfn [(num->digits [base num]
              (loop [n num res []]
                (if (zero? n)
                  res
                  (recur (long (/ n base)) (cons (mod n base) res)))))
          (quot-mod [base n]
            (list (quot n base) (mod n base)))
          (normalize [s] 
            (if (>= (count s) 4) 
              s
              (recur (cons '(0 0 0 0) s))))
          (convert [rn d]
            (flatten (map repeat rn d)))]
    (apply str (flatten 
                (map convert 
                  (normalize 
                    (map (fn [[a b c]] (cons a (cons b (quot-mod 4 c))))
                      (map (fn [[a b]] (cons a (quot-mod 5 b))) (map (partial quot-mod 9) (num->digits 10 n)))))
                  [["U" "U" "U" "M"] ["CM" "D" "CD" "C"] ["XC" "L" "XL" "X"] ["IX" "V" "IV" "I"]])))))

(comment

(load-file "src/bookexamples/forclojure/write-roman-numerals.clj")
(refer 'bookexamples.forclojure.write-roman-numerals)

(= "I" (write-roman-numerals 1))
(= "XXX" (write-roman-numerals 30))
(= "IV" (write-roman-numerals 4))
(= "CXL" (write-roman-numerals 140))
(= "DCCCXXVII" (write-roman-numerals 827))
(= "MMMCMXCIX" (write-roman-numerals 3999))
(= "XLVIII" (write-roman-numerals 48))

;; Some of the solutions on the web
;; I don't want to know what this does
(fn roman [x] (cond
    (<= 1000 x) (str "M" (roman (- x 1000)))
    (<= 900 x) (str "CM" (roman (- x 900)))
    (<= 500 x) (str "D" (roman (- x 500)))
    (<= 400 x) (str "CD" (roman (- x 400)))
    (<= 100 x) (str "C" (roman (- x 100)))
    (<= 90 x) (str "XC" (roman (- x 90)))
    (<= 50 x) (str "L" (roman (- x 50)))
    (<= 40 x) (str "XL" (roman (- x 40)))
    (<= 10 x) (str "X" (roman (- x 10)))
    (<= 9 x) (str "IX" (roman (- x 9)))
    (<= 5 x) (str "V" (roman (- x 5)))
    (<= 4 x) (str "IV" (roman (- x 4)))
    (<= 1 x) (str "I" (roman (- x 1)))
    true ""))

)