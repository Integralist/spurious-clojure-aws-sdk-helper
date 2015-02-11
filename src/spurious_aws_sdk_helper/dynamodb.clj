(ns spurious-aws-sdk-helper.dynamodb
  (:use [amazonica.aws.dynamodbv2])
  (:require [amazonica.core :refer [ex->map]]
            [clojure.java.shell :refer [sh]]
            [spurious-aws-sdk-helper.utils :refer [credentials]]))

; (sh "spurious ports --json")

(def cred (assoc credentials :endpoint "dynamodb.spurious.localhost:49155"))

(defn setup [schema]
  (try
    (create-table cred schema)
    (list-tables cred)
    (catch Exception e
      (clojure.pprint/write (ex->map e)))))
