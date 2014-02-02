(ns bookexamples.programmingclojure.chapter2.exploringclojure.calling-java 
  (:import java.util.Random))

(def rnd (new Random 10))
(. rnd nextInt)

(. rnd nextInt 10)

(import '(java.util Random Locale)
        '(java.text MessageFormat))

(new Locale)


(defn is-small? [number]
  (if (< number 100) "yes"
    (do 
      (println "Got a big number:" number)
      "no")))

(is-small? 200)

(loop [result [] x 5]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

(defn countdown [result x]
  (if (zero? x) 
    result
    (recur (conj result x) (dec x))))

(countdown [] 5)
(countdown #{} 5)
(countdown () 5)

(vec (reverse (rest (range 6))))
 (range 6)
 