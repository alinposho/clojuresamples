(ns bookexamples.programmingclojure.chapter8.multimethods.creating-ad-hoc-taxonomies)


(defstruct account :id :tag :balance)

(def savings-account (struct account 1 :Savings 100M))
(def checking-account (struct account 2 :Checking 250M))

(defmulti interest-rate :tag)
(defmethod interest-rate :Checking [_] 0M)
(defmethod interest-rate :Savings [_] 0.05M)

;(interest-rate savings-account)

(defmulti account-level :tag)
(defmethod account-level :Checking [acct]
  (if (>= (:balance acct) 5000) :Premium :Basic))
(defmethod account-level :Savings [acct]
  (if (>= (:balance acct) 1000) :Premium :Basic))

;(account-level savings-account)
;(account-level checking-account)

(defmulti service-charge (fn [acct] [(account-level acct) (:tag acct)]))
(defmethod service-charge [:Basic :Checking] [_] 25)
(defmethod service-charge [:Basic :Savings] [_] 10)
(defmethod service-charge [:Premium :Checking] [_] 0)
(defmethod service-charge [:Premium :Savings] [_] 0)

;; Notice that we do not need to create real accounts for testing
;(service-charge {:tag :Savings :balance 1000}) ;; premium savings account
;(service-charge {:tag :Checking :balance 1000}) ;; basic checking account

(def basic-savings (struct account 1 :Savings 100M))
(def basic-checking (struct account 2 :Checking 250M))
(def premium-savings (struct account 1 :Savings 1000M))
(def premium-checking (struct account 2 :Checking 5000M))

;(map service-charge [basic-savings basic-checking premium-savings premium-checking])

;; Define arbitrary parent child relationships with the derive function
(derive :Savings :Account) ;; The parent, in this case :Account can be of any type
(derive :Checking :Account)

(defmethod service-charge [:Premium :Account] [_] 0)
;(service-charge {:tag :Savings :balance 1000}) 
;(service-charge {:tag :Checking :balance 12000})



