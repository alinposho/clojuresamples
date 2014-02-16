(ns bookexamples.programmingclojure.chapter6.protocolsanddatatypes.datatypes
  (:use [bookexamples.programmingclojure.chapter6.protocolsanddatatypes.protocols])
  (:require [clojure.java.io :as io])
  (:import (java.security KeyStore KeyStore$SecretKeyEntry
                          KeyStore$PasswordProtection)
           (javax.crypto KeyGenerator Cipher CipherOutputStream
                         CipherInputStream)
           (java.io FileOutputStream FileInputStream)))

;; Define a simple datatype
(deftype CryptoVault [filename keystore password])

(def vault (->CryptoVault "file" "JCS" "toomanysecrets"))
(def vault (CryptoVault. "file" "JCS" "toomanysecrets"))

(.filename vault)
(.keystore vault)
(.password vault)


(defprotocol Vault
  (init-value [vault])
  (vault-output-stream [vault])
  (vault-input-stream [vault]))


(deftype CryptoVault [filename keystore password]
  Vault
  (init-value [vault]
    (let [password (.toCharArray (.password vault))
          key (.generateKey (KeyGenerator/getInstance "AES"))
          keystore (doto (KeyStore/getInstance "JCEKS")
                     (.load nil password)
                     (.setEntry "vault-key"
                       (KeyStore$SecretKeyEntry. key)
                       (KeyStore$PasswordProtection. password)))]
      (with-open [fos (FileOutputStream. (.keystore vault))]
        (.store keystore fos password))))
  (vault-output-stream [vault])
  (vault-input-stream [vault])
  IOFactory
  (make-reader [vault]
    (make-reader (vault-input-stream vault)))
  (make-writer [vault]
    (make-writer (vault-output-stream vault))))

(defn vault-key [vault]
  (let [password (.toCharArray (.password vault))]
    (with-open [fis (FileInputStream. (.keystore vault))]
      (-> (doto (KeyStore/getInstance "JCEKS")
            (.load fis password))
        (.getKey "vault-key" password)))))











