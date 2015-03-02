(ns bookexamples.paip.chapter2.a-rule-based-solution)
;;; 2.3 A rule based solution

(def ^{:dynamic true, :doc " A grammar for a trivial subset o f English."}
  *simple-grammar*
  '((sentence -> (noun-phrase verb-phrase))
     (noun-phrase -> (Article Noun))
     (verb-phrase -> (Verb noun-phrase))
     (Article -> the a)
     (Noun -> man ball woman table)
     (Verb -> hit took s a w liked)))

(def  ^{:dynamic true, 
        :doc "The grammar used by generate. Initially, this is *simple-grammar*,but we can switch to other grammars."}
  *grammar* *simple-grammar*)

(defn find-lst [key lst-of-lst]
  (some (fn [lst] (if (= (first lst) key) lst nil))
        lst-of-lst))

(defn rule-lhs [rule]
  "The left-hand side of a rule."
  (first rule))
(defn rule-rhs [rule]
  "The right-hand side of a rule."
  (rest(rest rule)))
(defn rewrites [category]
  "Return a list of the possible rewrites for this category."
  (rule-rhs (find-lst category *grammar*)))


(defn generate [phrase]
  "Generate a random sentence or phrase"
  (cond 
    (seq? phrase) (mapcat generate phrase)
    (rewrites phrase) (generate (first (rewrites phrase)))
     :else (list phrase)))

(comment
  *simple-grammar*
  *grammar*

  (rule-lhs '(noun-phrase -> (Article Noun)))
  (rule-rhs '(noun-phrase -> (Article Noun)))

  (find-lst 'Noun *grammar*)
  (find-lst 'sentence *grammar*)
  (rule-rhs (find-lst 'sentence *grammar*))

  (generate 'sentence)

  (rewrites 'sentence)
  (find-lst 'sentence *grammar*)
  (find-lst :blah *grammar*)
  
  (rand-nth (rewrites 'sentence))
  )