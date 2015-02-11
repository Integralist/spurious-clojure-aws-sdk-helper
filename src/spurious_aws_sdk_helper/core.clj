(ns spurious-aws-sdk-helper.core
  (:require [spurious-aws-sdk-helper.s3 :as s3]
            [spurious-aws-sdk-helper.sqs :as sqs]
            [spurious-aws-sdk-helper.dynamodb :as db]
            [clj-yaml.core :as yaml]))

(defn configure [opts]
  (do
    (if-let [name   (:s3  opts)] (s3/setup  name))
    (if-let [name   (:sqs opts)] (sqs/setup name))
    (if-let [schema (:ddb opts)] (ddb/setup (yaml/parse-string schema))))
