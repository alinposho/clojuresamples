(ns bookexamples.util.jar-matching
  [:require [clojure.string :as str]])

;; Functions for finding matching jar names from the RPM and the build location
(defn strip-path [s]
  "Extract the jar name from a string containg full or partial jar path e.g.
  './tomcat/webapps/orchestrationManager/WEB-INF/lib/com.workday.monolithic.jar' -> 'com.workday.monolithic.jar'"
  (last (str/split s #"/")))

(defn read-lines
  "Read the lines from a file and return as a set"
  [file]
  (with-open [rdr (clojure.java.io/reader file)]
    (set (line-seq rdr))))


(defn read-jars [file]
  (set (map strip-path (read-lines file))))

(comment
  (def s "./tomcat/webapps/orchestrationManager/WEB-INF/lib/com.workday.monolithic.jar")
  (str/split s #"/")

  (strip-path s)
  (strip-path "./lib/akka-actor.jar")


  (def suv-jars (read-jars "/Users/alin.posorovaschi/build/esbdocs/rpm_jars/esb_jars.out"))
  (def ccdev-jars (read-jars "/Users/alin.posorovaschi/build/esbdocs/rpm_jars/ccdev_jars.out"))

  (first suv-jars)
  (set? suv-jars)

  (println (str/join "\n" ccdev-jars))
  (println (str/join "\n" suv-jars))

  (count ccdev-jars)
  (count suv-jars)

  (def common-jars (clojure.set/intersection suv-jars ccdev-jars))
  (count common-jars)
  (println (str/join "\n" common-jars))
  (def document-jars #{"junit.jar" "servletApi.jar" "jetty.jar" "easymock.jar" "mockWebServer.jar" "objenesis.jar"
                       "scalaMock.jar" "scalatest.jar" "akka.jar" "scala.jar" "js.jar" "jaxrpc-api-1_0.jar" "wsdl4j-1_5_1p1.jar"
                       "xws-security.jar" "tubes.jar" "Catalina.jar" "mvel.jar" "servlet-api-3_0.jar" "xacml-1_0_patch1.jar"
                       "smackx.jar" "smack.jar" "stax2-api.jar" "xalan-2_7_1.jar" "iText.jar"  "ipworksedi.jar" "EDI.jar"
                       "wstrust-api.jar" "W8XMLConverters.jar" "akka-actor.jar" "akka-remote.jar" "akka-slf4j_2.10-2.3.4.jar"
                       "config.jar" "netty.jar" "protobuf-java-2.5.0-spark.jar"})
  (println (str/join "\n"
                     (sort (clojure.set/difference common-jars document-jars))))

  )