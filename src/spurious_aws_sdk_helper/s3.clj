(ns spurious-aws-sdk-helper.s3
  (:use [amazonica.aws.s3])
  (:require [amazonica.core :refer [ex->map]]
            [clojure.java.shell :refer [sh]]
            [clojure.data.json :as json]
            [spurious-aws-sdk-helper.utils :refer [credentials]]))

(def endpoint (first
                (:spurious-s3
                  (json/read-str
                    (:out (sh "spurious" "ports" "--json")) :key-fn keyword))))

(def cred (assoc
            credentials
            :endpoint (str (:Host endpoint) ":" (:HostPort endpoint))))

(defn setup [name]
  (try
    (set-s3client-options cred :path-style-access true)
    (create-bucket cred name)
    (catch Exception e
      (clojure.pprint/write (ex->map e)))))
