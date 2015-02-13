(ns spurious-aws-sdk-helper.s3
  (:use [amazonica.aws.s3])
  (:require [amazonica.core :refer [ex->map]]
            [spurious-aws-sdk-helper.utils :refer [endpoint credentials]]))

(def s3 (endpoint :spurious-s3))

(def cred
  (assoc credentials :endpoint (str (:Host s3) ":" (:HostPort s3))))

(defn setup [name]
  (try
    (set-s3client-options cred :path-style-access true)
    (create-bucket cred name)
    (catch Exception e
      (clojure.pprint/write (ex->map e)))))
