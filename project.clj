(defproject bookexamples "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"] 
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.clojure/tools.logging "0.2.3"]
                 [log4j "1.2.16"]
                 [javax.mail/mail "1.4.1"]
                 [org.clojure/core.async "0.1.278.0-76b25b-alpha"]]
  ; :main ^:skip-aot bookexamples.programmingclojure.chapter9.real.world.example.pinger-main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-idefiles "0.2.0"]])
