(ns spurious-aws-sdk-helper.sqs
  (:use [amazonica.aws.sqs])
  (:require [amazonica.core :refer [ex->map]]
            [clojure.java.shell :refer [sh]]
            [spurious-aws-sdk-helper.utils :refer [credentials]]))

; (sh "spurious ports --json")

(def cred (assoc credentials :endpoint "sqs.spurious.localhost:49153"))

(defn setup [name]
  (try
    (create-queue cred name)
    (catch Exception e
      (clojure.pprint/write (ex->map e)))))
