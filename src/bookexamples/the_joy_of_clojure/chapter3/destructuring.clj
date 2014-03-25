(ns the_joy_of_clojure.destructuring)

; (re-seq #"\d" "clojure 1.1.0")

;; This will fail since we have the "& rest" construct that requires a sequence
 (let [[first second & rest] (re-matcher #"\d" "clojure 1.1.0")]
;; This does not seem to work either: first and second are not bound to anything.
(let [[first second] (re-matcher #"\d" "clojure 1.1.0")]
	(println (str "first=" first))
	(println (str "second=" second))
	)

(let [range-vec (range 10)
	  [a b c & more :as all] range-vec]
	  (println "a b c are " a b c)
	  (println "more is " more)
	  (println "all is " all))

(def guys-name-map
      {:f-name "Guy" :m-name "Lewis" :l-name "Steele"})

;; Notice that the keys are on the right hand side instead of the left. That's because on the lefthand side you are supposed to put special keywords like: keys
(let [{f-name :f-name m-name :m-name l-name :l-name} guys-name-map]
	(str l-name ", " f-name " " m-name))

;; A better way of extracting values for a list of keywords is doing this:
(let [{:keys [f-name m-name l-name]} guys-name-map]
	(str l-name ", " f-name " " m-name))

;; Just in care your map ==has strings as keywords us :strs
(let [{:strs [f-name m-name l-name]} {"f-name" "Guy" "m-name" "Lewis" "l-name" "Steele"}]
	(str l-name ", " f-name " " m-name))

(let [{:strs [title f-name m-name l-name] :or {title "Mr."}} {"f-name" "Guy" "m-name" "Lewis" "l-name" "Steele"}]
	(str title " " l-name ", " f-name " " m-name))





