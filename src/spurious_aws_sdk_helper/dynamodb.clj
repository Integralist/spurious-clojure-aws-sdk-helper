(ns spurious-aws-sdk-helper.dynamodb
  (:use [amazonica.aws.dynamodbv2])
  (:require [spurious-aws-sdk-helper.utils :refer [endpoint cred]]))

(defn resource [type]
  (endpoint type :spurious-dynamo))

(defn setup [type schema]
  (try
    (let [credentials (cred (resource type))]
      (set-signer-region-override credentials "eu-west-1")
      (create-table credentials schema)
      (list-tables credentials))
    (catch Exception e
      (prn "DynamoDB Error: chances are you're creating a table that already exists"))))
