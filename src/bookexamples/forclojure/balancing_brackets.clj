(ns bookexamples.forclojure.balancing-brackets)


;; Problem 177: When parsing a snippet of code it's often a good idea to do a sanity check to see if all the brackets match up. Write a function that takes in a string and returns truthy if all square [ ] round ( ) and curly { } brackets are properly paired and legally nested, or returns falsey otherwise.
(defn balanced-brackets? [snippet]
  (leftn [(open-brancket? [ch] (#{\( \{ \[ } ch))
          (closing-bracket? [ch] (#{\) \} \]} ch))]
    ))


(comment

(load-file "src/bookexamples/forclojure/balancing_brackets.clj")
(refer 'bookexamples.forclojure.balancing-brackets)


;; Some of the solutions on the web

)