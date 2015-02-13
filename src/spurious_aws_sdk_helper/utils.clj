(ns spurious-aws-sdk-helper.utils
  (:require [clojure.data.json :as json]
            [clojure.java.shell :refer [sh]]))

(defn public-methods [namespace]
  (keys (ns-publics namespace)))

(defn endpoint [key]
  (first
    (key
      (json/read-str
        (:out (sh "spurious" "ports" "--json")) :key-fn keyword))))

(def credentials {:access-key "development_access"
                  :secret-key "development_secret"
                  :client-config {:protocol "http"}})
