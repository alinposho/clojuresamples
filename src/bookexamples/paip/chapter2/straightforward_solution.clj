(ns bookexamples.paip.straightforward-solution)

;;; 2.2 Straightforward Solution

(defn one-of [choices]
  "Picks an element at random, from the vector"
  (nth choices (rand-int (count choices))))

(defn noun []
  (list (one-of '(man ball woman table))))

(defn article [] 
  (list (one-of '(the a))))

(defn noun-phrase []
  (concat (article) (noun)))

(defn verb []
  (list (one-of '(hit took saw liked))))

(defn verb-phrase []
  (concat (verb) (noun-phrase)))

(defn sentence [] 
  (concat (noun-phrase) (verb-phrase)))

(comment
  (one-of [:a :b :c])
  (one-of '(:a :b :c))
  
  (noun-phrase)

  (sentence)
  
  )
