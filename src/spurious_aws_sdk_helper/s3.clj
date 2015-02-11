(ns spurious-aws-sdk-helper.s3
  (:use [amazonica.aws.s3])
  (:require [amazonica.core :refer [ex->map]]
            [clojure.java.shell :refer [sh]]
            [cheshire.core :refer [parse-string]]
            [spurious-aws-sdk-helper.utils :refer [credentials]]))

; (sh "spurious ports --json")

(def cred (assoc credentials :endpoint "s3.spurious.localhost:49154"))

(defn setup [name]
  (try
    (set-s3client-options cred :path-style-access true)
    (create-bucket cred name)
    (catch Exception e
      (clojure.pprint/write (ex->map e)))))
