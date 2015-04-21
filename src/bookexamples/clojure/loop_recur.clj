(ns bookexamples.clojure.loop-recur)

;;; An implementation of the loop-recur construct outside the Clojure compiler - taken form http://blog.8thlight.com/patrick-gombert/2015/03/23/tail-recursion-in-clojure.html

(defmacro my-loop [user-bindings & body]
          (let [finished (gensym "finished")
                bindings (gensym "bindings")]
               `(let [~finished (atom false)
                      ~bindings (atom '~user-bindings)])))


