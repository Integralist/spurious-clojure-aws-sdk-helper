(ns spurious-aws-sdk-helper.dynamodb
  (:use [amazonica.aws.dynamodbv2])
  (:require [amazonica.core :refer [ex->map]]
            [spurious-aws-sdk-helper.utils :refer [env credentials]]))

(def cred (assoc credentials :endpoint "dynamodb.spurious.localhost:49155"))

(try
  (create-table cred
                :table-name "testing"
                ...stuff...)
  (catch Exception e
    (clojure.pprint/write (ex->map e))))
