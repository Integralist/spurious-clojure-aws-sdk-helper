(ns spurious-aws-sdk-helper.s3
  (:use [amazonica.aws.s3])
  (:require [amazonica.core :refer [ex->map]]
            [spurious-aws-sdk-helper.utils :refer [env credentials]]))

(def cred (assoc credentials :endpoint "s3.spurious.localhost:49154"))

(try
  (set-s3client-options cred :path-style-access true)
  (create-bucket cred "testing")
  (catch Exception e
    (clojure.pprint/write (ex->map e))))
