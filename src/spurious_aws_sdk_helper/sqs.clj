(ns spurious-aws-sdk-helper.sqs
  (:use [amazonica.aws.sqs])
  (:require [amazonica.core :refer [ex->map]]
            [spurious-aws-sdk-helper.utils :refer [env credentials]]))

(def cred (assoc credentials :endpoint "sqs.spurious.localhost:49153"))

(try
  (create-queue cred "testing")
  (catch Exception e
    (clojure.pprint/write (ex->map e))))
