(ns bookexamples.forclojure.anagrams)

;; A function to compute Euler anagrams totient function
(defn anagrams [xs]
	(letfn [(anagram? [a b]
						(let [sa (set a)
									sb (set b)]
							(= sa sb (clojure.set/intersection (set a) (set b)))))]
	(set 
		(filter 
			#(< 1 (count %)) 
			(set (map 
							(fn [s] (set (filter #(anagram? s %) xs))) 
							xs))))))


(comment

(load-file "src/bookexamples/forclojure/anagrams.clj")
(refer 'bookexamples.forclojure.anagrams)


(anagrams? "abc" "bac")

(= (anagrams ["meat" "mat" "team" "mate" "eat"])
   #{#{"meat" "team" "mate"}})
(= (anagrams ["veer" "lake" "item" "kale" "mite" "ever"])
   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})



;; Some of the solutions on the web
;; I don't want to know what this does
(fn [v]
  (into #{}
    (map set
      (filter #(> (count %) 1)
        (map val (group-by sort v))))))


)