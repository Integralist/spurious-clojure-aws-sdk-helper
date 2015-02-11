(ns spurious-aws-sdk-helper.utils)

(defn env [name]
  (System/getenv name))

(defn public-methods [namespace]
  (keys (ns-publics namespace)))

(def credentials {:access-key "development_access"
                  :secret-key "development_secret"
                  :client-config {:protocol "http"}})
