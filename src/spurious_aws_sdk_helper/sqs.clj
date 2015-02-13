(ns spurious-aws-sdk-helper.sqs
  (:use [amazonica.aws.sqs])
  (:require [amazonica.core :refer [ex->map]]
            [spurious-aws-sdk-helper.utils :refer [endpoint credentials]]))

(def sqs (endpoint :spurious-sqs))

(def cred
  (assoc credentials :endpoint (str (:Host sqs) ":" (:HostPort sqs))))

(defn setup [name]
  (try
    (create-queue cred name)
    (catch Exception e
      (clojure.pprint/write (ex->map e)))))
