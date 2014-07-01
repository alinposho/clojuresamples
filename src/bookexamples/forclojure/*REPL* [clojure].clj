nREPL server started on port 56156 on host 127.0.0.1 - nrepl://127.0.0.1:56156
REPL-y 0.3.1
Clojure 1.6.0
    Docs: (doc function-name-here)
          (find-doc "part-of-name-here")
  Source: (source function-name-here)
 Javadoc: (javadoc java-object-or-class-here)
    Exit: Control+D or (exit) or (quit)
 Results: Stored in vars *1, *2, *3, an exception in *e

user=> (class Class)
(class Class)
java.lang.Class
user=> (= (class Class) Class)
(= (class Class) Class)
true
user=> (and Class true)
(and Class true)
true
user=> (partition-all 2 1 [38 + 48 - 2 / 2])
(partition-all 2 1 [38 + 48 - 2 / 2])
((38 #<core$_PLUS_ clojure.core$_PLUS_@1aac1837>) (#<core$_PLUS_ clojure.core$_PLUS_@1aac1837> 48) (48 #<core$_ clojure.core$_@40862e24>) (#<core$_ clojure.core$_@40862e24> 2) (2 #<core$_SLASH_ clojure.core$_SLASH_@783db4e5>) (#<core$_SLASH_ clojure.core$_SLASH_@783db4e5> 2) (2))
user=> (reduce (map #(%2 %1) reverse (partition-all 2 1 [38 + 48 - 2 / 2])))
(reduce (map #(%2 %1) reverse (partition-all 2 1 [38 + 48 - 2 / 2])))

ArityException Wrong number of args (1) passed to: core/reduce  clojure.lang.AFn.throwArity (AFn.java:429)
user=> (reduce  #(%2 %1) (map reverse (partition-all 2 1 [38 + 48 - 2 / 2])))
(reduce  #(%2 %1) (map reverse (partition-all 2 1 [38 + 48 - 2 / 2])))

ClassCastException clojure.lang.PersistentList cannot be cast to clojure.lang.IFn  user/eval716/fn--717 (NO_SOURCE_FILE:1)
user=> (eval '(+ 1 2))
(eval '(+ 1 2))
3
user=> (reduce  #(eval (cons %2 %1)) (map reverse (partition-all 2 1 [38 + 48 - 2 / 2])))
(reduce  #(eval (cons %2 %1)) (map reverse (partition-all 2 1 [38 + 48 -  2 / 2])))

ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn  user/eval730 (NO_SOURCE_FILE:1)
user=> (reduce  #(eval (conj %2 %1)) (map reverse (partition-all 2 1 [38 + 48 - 2 / 2])))
(reduce  #(eval (conj %2 %1)) (map reverse (partition-all 2 1 [38 + 48 -  2 / 2])))

ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn  user/eval738 (NO_SOURCE_FILE:1)
user=> (reduce  #(eval (conj %2 %1)) (map (comp vector reverse) (partition-all 2 1 [38 + 48 - 2 / 2])))
(reduce  #(eval (conj %2 %1)) (map (comp vector reverse) (partition-all 2  1 [38 + 48 - 2 / 2])))

ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn  user/eval746 (NO_SOURCE_FILE:1)
user=> (map (comp vector reverse) (partition-all 2 1 [38 + 48 - 2 / 2]))
(map (comp vector reverse) (partition-all 2 1 [38 + 48 - 2 / 2]))
([(#<core$_PLUS_ clojure.core$_PLUS_@1aac1837> 38)] [(48 #<core$_PLUS_ clojure.core$_PLUS_@1aac1837>)] [(#<core$_ clojure.core$_@40862e24> 48)] [(2 #<core$_ clojure.core$_@40862e24>)] [(#<core$_SLASH_ clojure.core$_SLASH_@783db4e5> 2)] [(2 #<core$_SLASH_ clojure.core$_SLASH_@783db4e5>)] [(2)])
user=> (map (comp vec reverse) (partition-all 2 1 [38 + 48 - 2 / 2]))
(map (comp vec reverse) (partition-all 2 1 [38 + 48 - 2 / 2]))
([#<core$_PLUS_ clojure.core$_PLUS_@1aac1837> 38] [48 #<core$_PLUS_ clojure.core$_PLUS_@1aac1837>] [#<core$_ clojure.core$_@40862e24> 48] [2 #<core$_ clojure.core$_@40862e24>] [#<core$_SLASH_ clojure.core$_SLASH_@783db4e5> 2] [2 #<core$_SLASH_ clojure.core$_SLASH_@783db4e5>] [2])
user=> (reduce  #(eval (conj %2 %1)) (map (comp vec reverse) (partition-all 2 1 [38 + 48 - 2 / 2])))
(reduce  #(eval (conj %2 %1)) (map (comp vec reverse) (partition-all 2 1  [38 + 48 - 2 / 2])))
[2 [2 #<core$_SLASH_ clojure.core$_SLASH_@239017ef> [#<core$_SLASH_ clojure.core$_SLASH_@5c4a9f9d> 2 [2 #<core$_ clojure.core$_@4f0d979f> [#<core$_ clojure.core$_@4c250432> 48 [48 #<core$_PLUS_ clojure.core$_PLUS_@6f295d36> [#<core$_PLUS_ clojure.core$_PLUS_@67978139> 38]]]]]]]
user=> (eval [+ 1 2])
(eval [+ 1 2])
[#<core$_PLUS_ clojure.core$_PLUS_@4b8a0d1f> 1 2]
user=> (reduce  #(eval (reverse (cons %2 %1))) (partition-all 2 1 [38 + 48 - 2 / 2]))
(reduce  #(eval (reverse (cons %2 %1))) (partition-all 2 1 [38 + 48 - 2 /  2]))

IllegalArgumentException Don't know how to create ISeq from: java.lang.Long  clojure.lang.RT.seqFrom (RT.java:505)
user=> (partition-all 2 1 [38 + 48 - 2 / 2])
(partition-all 2 1 [38 + 48 - 2 / 2])
((38 #<core$_PLUS_ clojure.core$_PLUS_@1aac1837>) (#<core$_PLUS_ clojure.core$_PLUS_@1aac1837> 48) (48 #<core$_ clojure.core$_@40862e24>) (#<core$_ clojure.core$_@40862e24> 2) (2 #<core$_SLASH_ clojure.core$_SLASH_@783db4e5>) (#<core$_SLASH_ clojure.core$_SLASH_@783db4e5> 2) (2))
user=> (reduce  #(reverse (cons %2 %1)) (partition-all 2 1 [38 + 48 - 2 / 2]))
(reduce  #(reverse (cons %2 %1)) (partition-all 2 1 [38 + 48 - 2 / 2]))
((#<core$_SLASH_ clojure.core$_SLASH_@783db4e5> 2) (#<core$_ clojure.core$_@40862e24> 2) (#<core$_PLUS_ clojure.core$_PLUS_@1aac1837> 48) 38 #<core$_PLUS_ clojure.core$_PLUS_@1aac1837> (48 #<core$_ clojure.core$_@40862e24>) (2 #<core$_SLASH_ clojure.core$_SLASH_@783db4e5>) (2))
user=> (eval '(+ 1 '(* 2 3)))
(eval '(+ 1 '(* 2 3)))

ClassCastException clojure.lang.PersistentList cannot be cast to java.lang.Number  clojure.lang.Numbers.add (Numbers.java:126)
user=> (eval '(+ 1 (* 2 3)))
(eval '(+ 1 (* 2 3)))
7
user=> '(+ 1 (* 2 3))
'(+ 1 (* 2 3))
(+ 1 (* 2 3))
user=> (map #(reverse (cons %2 %1)) (partition-all 2 1 [38 + 48 - 2 / 2]))
(map #(reverse (cons %2 %1)) (partition-all 2 1 [38 + 48 - 2 / 2]))

ArityException Wrong number of args (1) passed to: user/eval802/fn--803  clojure.lang.AFn.throwArity (AFn.java:429)
user=> (reduce #(eval (list (conj %2 %1))) 38 (partition-all 2 1 [+ 48 - 2 / 2]))
(reduce #(eval (list (conj %2 %1))) 38 (partition-all 2 1 [+ 48 - 2 / 2]) )

ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn  user/eval812 (NO_SOURCE_FILE:1)
user=> (partition-all 2 1 [+ 48 - 2 / 2])
(partition-all 2 1 [+ 48 - 2 / 2])
((#<core$_PLUS_ clojure.core$_PLUS_@1aac1837> 48) (48 #<core$_ clojure.core$_@40862e24>) (#<core$_ clojure.core$_@40862e24> 2) (2 #<core$_SLASH_ clojure.core$_SLASH_@783db4e5>) (#<core$_SLASH_ clojure.core$_SLASH_@783db4e5> 2) (2))
user=> (partial (eval '(+ 2)) 3)
(partial (eval '(+ 2)) 3)
#<core$partial$fn__4228 clojure.core$partial$fn__4228@d898b89>
user=> ((partial (eval '(+ 2))) 3)
((partial (eval '(+ 2))) 3)
ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn  user/eval820 (NO_SOURCE_FILE:1)

user=> (into '(38) '(+ 3))
(into '(38) '(+ 3))
(3 + 38)
user=> (concat '(+ 3) '(38))
(concat '(+ 3) '(38))
(+ 3 38)
user=> (partition-all 2 1 [+ 48 - 2 / 2])
(partition-all 2 1 [+ 48 - 2 / 2])
((#<core$_PLUS_ clojure.core$_PLUS_@1aac1837> 48) (48 #<core$_ clojure.core$_@40862e24>) (#<core$_ clojure.core$_@40862e24> 2) (2 #<core$_SLASH_ clojure.core$_SLASH_@783db4e5>) (#<core$_SLASH_ clojure.core$_SLASH_@783db4e5> 2) (2))
user=> (partition-all 2 [+ 48 - 2 / 2])
(partition-all 2 [+ 48 - 2 / 2])
((#<core$_PLUS_ clojure.core$_PLUS_@1aac1837> 48) (#<core$_ clojure.core$_@40862e24> 2) (#<core$_SLASH_ clojure.core$_SLASH_@783db4e5> 2))
user=> (eval '(+ 3 (1)))
(eval '(+ 3 (1)))

ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn  user/eval834 (NO_SOURCE_FILE:1)
user=> (eval '(+ 3 (identity 1)))
(eval '(+ 3 (identity 1)))
4
user=> (reduce #(concat %2 %1) '(identity 38) (partition-all 2 [+ 48 - 2 / 2]))
(reduce #(concat %2 %1) '(identity 38) (partition-all 2 [+ 48 - 2 / 2]))
(#<core$_SLASH_ clojure.core$_SLASH_@783db4e5> 2 #<core$_ clojure.core$_@40862e24> 2 #<core$_PLUS_ clojure.core$_PLUS_@1aac1837> 48 identity 38)
user=> (reduce #(concat %2 (list %1)) '(identity 38) (partition-all 2 [+ 48 - 2 / 2]))