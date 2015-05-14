(ns bookexamples.util.functions)


(defn to-traceable
  "Returns a function that traces(prints) the input parameters
  and result of the function f at each step of fs invokation. This should
  make it easier to understand what recursive function calls are doing"
  [f]
  (letfn [(traceblef [& params]
                     (println "intput parameters" params)
                     (let [res (apply f params)]
                       (println "result=" res)
                       res))]
    traceblef))


(comment

  ;; lets try out our to-traceable function
  (def treduce (to-traceable reduce))
  (treduce + (range 1 10))

  (defn sum [xs]
    (if (seq xs)
      (+ (first xs) (sum (rest xs)))
      0))

  ((to-traceable sum) (range 1 4)) ;; This will not print the neste calls
  )


