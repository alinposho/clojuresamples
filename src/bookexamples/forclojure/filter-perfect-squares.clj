(ns bookexamples.forclojure.filter-perfect-squares)

(defn filter-perfect-squares [s] 
  (letfn [(perfect-square [n] 
               (zero? (mod n (Math/sqrt n))))]
   (clojure.string/join "," 
    (filter 
     perfect-square
     (map #(Integer/valueOf %) (re-seq #"\d+" s))))))


(comment

(load-file "src/bookexamples/forclojure/filter-perfect-squares.clj")
(refer 'bookexamples.forclojure.filter-perfect-squares)

(filter-perfect-squares "4,5,6,7,8,9")
(= (filter-perfect-squares "4,5,6,7,8,9") "4,9")
(= (filter-perfect-squares "15,16,25,36,37") "16,25,36")
;; Some of the solutions on the web
(fn [x] (apply str (interpose "," (filter (fn [x] (some #{x} (map-indexed * (range x)))) (read-string (str "(" x ")"))))))

;; For some insight on how this solution works
(#(read-string (str "(" % ")")) "4,5,6,7")
;; The following line multiplies the index with the element at that index, which is equal to the index
(map-indexed * (range 10))


)