(ns spurious-aws-sdk-helper.dynamodb
  (:use [amazonica.aws.dynamodbv2])
  (:require [amazonica.core :refer [ex->map]]
            [spurious-aws-sdk-helper.utils :refer [endpoint credentials]]))

(def ddb (endpoint :spurious-dynamo))

(def cred
  (assoc credentials :endpoint (str (:Host ddb) ":" (:HostPort ddb))))

(defn setup [schema]
  (try
    (create-table cred schema)
    (list-tables cred)
    (catch Exception e
      (clojure.pprint/write (ex->map e)))))
