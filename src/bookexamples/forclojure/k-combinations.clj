(ns bookexamples.forclojure.k-combinations)

;; Problem 103: 
;; Given a sequence S consisting of n elements generate all k-combinations of S, i. e. generate all possible sets consisting of k distinct elements taken from S. The number of k-combinations for a sequence is equal to the binomial coefficient.

(defn k-combinations [k st]
  (cond 
    (> k (count st)) #{}
    (= 1 k) (set (map hash-set st))
    :else 
      (set 
        (for [s st
              subst (k-combinations (dec k) st)
              :let [nsubst (conj subst s)]
              :when (= (count nsubst) k)]
          nsubst))))


(comment

(load-file "src/bookexamples/forclojure/k-combinations.clj")
(refer 'bookexamples.forclojure.k-combinations)

(list
	(= (k-combinations 1 #{4 5 6}) #{#{4} #{5} #{6}})
	(= (k-combinations 10 #{4 5 6}) #{})
	(= (k-combinations 2 #{0 1 2}) #{#{0 1} #{0 2} #{1 2}})
	(= (k-combinations 3 #{0 1 2 3 4}) #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
	                         #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}})
	(= (k-combinations 4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}})
	(= (k-combinations 2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
	                                    #{:a "abc"} #{:a "efg"} #{"abc" "efg"}})
)

;; Some of the solutions on the web
(fn a [n k]
    (if (= 0 n) #{#{}}
      (reduce into #{}
              (map (fn [x] (map #(conj % x)
                                     (a (dec n) 
                                        (disj k x))))
                   k))))

(fn [k s]
  (set (filter #(= k (count %)) (reduce #(concat %1 (map (fn [i] (set (conj i %2))) %1)) #{#{}} s))))


)