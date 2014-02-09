(ns bookexamples.programmingclojure.chapter5.state.per-thread-state
  [:import [java.lang.Thread]])

(def ^:dynamic foo 10)
foo

(.start (Thread. (fn [] (println foo))))

(binding [foo 42] foo)

(defn print-foo [] (println foo))

;; This will print 10
(let [foo "let foo"]
  (print-foo))

;; This will print the value from the binding 
(binding [foo 42] (print-foo))

(defn ^:dynamic slow-double [n];; The ^:dynamic sets the metadata for this method, in this case the :dynamic property is set to true 
  (Thread/sleep 100)
  (* n n))

(time (slow-double 4))

(defn call-with-slow-double [] 
  (map slow-double [1 2 1 2 1 2]))

(time (dorun (call-with-slow-double)))

(defn demo-memoize []
  (time 
    (dorun 
      (binding [slow-double (memoize slow-double)] ;; this only works if slow-dynamic is dynamiclly bound
        (call-with-slow-double)))))

(demo-memoize)

