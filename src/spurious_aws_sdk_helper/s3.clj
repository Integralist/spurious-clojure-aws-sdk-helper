(ns spurious-aws-sdk-helper.s3
  (:use [amazonica.aws.s3])
  (:require [spurious-aws-sdk-helper.utils :refer [endpoint cred]]))

(defn resource [type]
  (endpoint type :spurious-s3))

(defn setup
  ([type]
   (set-s3client-options (cred (resource type)) :path-style-access true))
  ([type name]
   (try
     (let [credentials (cred (resource type))]
       (set-s3client-options credentials :path-style-access true)
       (create-bucket credentials name))
     (catch Exception e
       (prn "S3 Error: chances are you're creating a bucket that already exists")))))
