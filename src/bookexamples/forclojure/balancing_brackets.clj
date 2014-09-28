(ns bookexamples.forclojure.balancing-brackets)


;; Problem 177: When parsing a snippet of code it's often a good idea to do a sanity check to see if all the brackets match up. Write a function that takes in a string and returns truthy if all square [ ] round ( ) and curly { } brackets are properly paired and legally nested, or returns falsey otherwise.
(defn balanced-brackets? [snippet]
  (letfn [(inverse [br] ({\( \), \{ \}, \[ \]} br))
          (open-brancket? [ch] (#{\( \{ \[ } ch))
          (closing-bracket? [ch] (#{\) \} \]} ch))
          (add-or-remove [[br & brkts :as allbrkts] ch]
            (cond
              (open-brancket? ch) (cons ch allbrkts)
              (closing-bracket? ch) (if (= (inverse br) ch) brkts (cons ch allbrkts))
              :else allbrkts ))]
  (empty? (reduce add-or-remove nil snippet))))


(comment

(load-file "src/bookexamples/forclojure/balancing_brackets.clj")
(refer 'bookexamples.forclojure.balancing-brackets)

(balanced-brackets? "This string has no brackets.")
(balanced-brackets? "(start, end]")
(not (balanced-brackets? "(start, end]"))
(not (balanced-brackets? "())"))
(not (balanced-brackets? "[ { ] } "))
(balanced-brackets? "(())")
(balanced-brackets? "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))")
(not (balanced-brackets? "([]([(()){()}(()(()))(([[]]({}([)))())]((((()()))))))"))
(not (balanced-brackets? "["))

;; Some of the solutions on the web

)
