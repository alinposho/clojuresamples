(ns the-joy-of-clojure.clojure-proxy
	(:import (com.sun.net.httpserver HttpHandler HttpExchange HttpServer)
	(java.net InetSocketAddress HttpURLConnection)
	(java.io IOException FilterOutputStream)
	(java.util Arrays)))

;; Create service
(defn new-server [port path handler]
	(doto (HttpServer/create (InetSocketAddress. port) 0)
		(.createContext path handler)
		(.setExecutor nil)
		(.start)))

;; Clojure's way of "extending" classes - the "proxy" construct
(defn default-handler [txt]
	(proxy [HttpHandler] []
		(handle [exchange]
			(.sendResponseHeaders exchange HttpURLConnection/HTTP_OK 0)
			(doto (.getResponseBody exchange)
				  (.write (.getBytes txt)) ; Close over txt
				  (.close)))))

; (def server (new-server 8123
; 						"/joy/hello"
; 						(default-handler "Hello Cleveland")))

; (.stop server 0)

(def p (default-handler
		"There's no problem that can't be solved
		with another level of indirection"))

(defn make-handler-fn [fltr txt]
	(fn [this exchange]
		(let [b (.getBytes txt)]
			(-> exchange .getResponseHeaders
						(.set "Content-Type" "text/html"))
						(.sendResponseHeaders exchange HttpURLConnection/HTTP_OK 0)
			(doto (fltr (.getResponseBody exchange))
					(.write b)
					(.close)))))

(defn change-message
	"Convenience method to change a proxy's output message"
	([p txt] (change-message p identity txt))
	([p fltr txt]
		(update-proxy p
		{"handle" (make-handler-fn fltr txt)})))

(defn screaming-filter [o]
	(proxy [FilterOutputStream] [o]
		(write [b]
			(proxy-super write (.getBytes (str "<strong>" (.toUpperCase (String. b)) "<strong>"))))))

;; Start stop the server
; (def server (new-server 8123 "/joy/hello" p))

;; Change message while the server is still running
; (change-message p "Our new message")
;; Add a filter that makes the message uppercase
; (change-message p screaming-filter "screaming-filter")

; (.stop server 0)

;; Inspecting the proxy mappings, here p is the proxy
; (proxy-mappings p)

