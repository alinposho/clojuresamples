(ns bookexamples.programmingclojure.chapter3.unifyind.data.with.sequences.calling-structure-specific-functions)

(def song {:name "Agnus Dei"
           :artist "Krzysztof Penderecki"
           :album "Polish Requiem" 
           :genre "Classical"})

(assoc song :kind  "MP3")
(dissoc song :genre)
(contains? (dissoc song :genre) :genre)
(select-keys song [:name :artist])
;; Notice that for common keys the right collection wins
(merge song {:size 8118166, :time 507245, :genre "retro"})