(ns bookexamples.forclojure.read-roman-numerals)

;; Problem 92: Roman numerals are easy to recognize, but not everyone knows all the rules necessary to work with them. Write a function to parse a Roman-numeral string and return the number it represents. 
  ;;You can assume that the input will be well-formed, in upper-case, and follow the subtractive principle. You don't need to handle any numbers greater than MMMCMXCIX (3999), the largest number representable with ordinary letters.
(defn read-roman-numerals [[x & xs]]
  (let [m {\M 1000, \D 500, \C 100, \L 50, \X 10, \V 5, \I 1}
        d (m x)]
  (if (empty? xs) 
    d
    ((if (< (m x) (m (first xs))) - +) 
      (read-roman-numerals xs) 
      d))))

;; This does not work for lazy seqs and it's not complete
(reduce concat (map (fn [[a b]](if (< a b) [a :less b] [a b])) (partition 2 1 [1 6 7 4 3])))

(comment

(load-file "src/bookexamples/forclojure/read-roman-numerals.clj")
(refer 'bookexamples.forclojure.read-roman-numerals)

(= 14 (read-roman-numerals "XIV"))
(= 827 (read-roman-numerals "DCCCXXVII"))
(= 3999 (read-roman-numerals "MMMCMXCIX"))
(= 48 (read-roman-numerals "XLVIII"))

;; I don't want to know what this does
#(let [
    letter ["M" "CM" "D" "CD" "C" "XC" "L" "XL" "X" "IX" "V" "IV" "I"]
    value [1000 900 500 400 100 90 50 40 10 9 5 4 1]
    l2v (zipmap letter value)
    lre (re-pattern (apply str (interpose "|" letter)))]
    (apply + (map l2v (re-seq lre %))))


)