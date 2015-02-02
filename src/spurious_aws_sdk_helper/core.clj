(ns spurious-aws-sdk-helper.core
  (:require [spurious-aws-sdk-helper.s3 :as s3]
            [spurious-aws-sdk-helper.sqs :as sqs]
            [spurious-aws-sdk-helper.dynamodb :as ddb]
            [clj-yaml.core :as yaml]))

(defn configure [type opts]
  (if-let [name   (:s3  opts)] (s3/setup  type name))
  (if-let [name   (:sqs opts)] (sqs/setup type name))
  (if-let [schema (:ddb opts)] (ddb/setup type (yaml/parse-string schema))))
