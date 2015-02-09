(ns spurious-aws-sdk-helper.core
  (:require [spurious-aws-sdk-helper.utils :refer [env]]
            [spurious-aws-sdk-helper.s3]))

(prn (env "GREETING"))
