(ns spurious-aws-sdk-helper.sqs
  (:use [amazonica.aws.sqs])
  (:require [spurious-aws-sdk-helper.utils :refer [endpoint cred]]))

(defn resource [type]
  (endpoint type :spurious-sqs))

(defn setup [type name]
  (try
    (create-queue (cred (resource type)) name)
    (catch Exception e
      (prn "SQS Error: chances are you're creating a queue that already exists"))))
