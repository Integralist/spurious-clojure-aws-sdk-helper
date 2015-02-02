(ns spurious-aws-sdk-helper.utils
  (:require [environ.core :refer [env]]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.java.shell :refer [sh]]))

(defn public-methods [namespace]
  (keys (ns-publics namespace)))

(defn- service [id suffix]
  (keyword (str id "-spurious-localhost-" suffix)))

(defn- split [type pattern]
  (last (str/split type pattern)))

(defn- extract [id suffix pattern]
  (if-let [type (env (service id suffix))]
    (split type pattern)))

(defn- host [id]
  (extract id "name" #"/"))

(defn- port [id]
  (extract id "port" #":"))

(defn- docker-source []
  {:spurious-dynamo [{:Host (host "dynamodb") :HostPort (port "dynamodb")}]
   :spurious-sqs    [{:Host (host "sqs")      :HostPort (port "sqs")}]
   :spurious-s3     [{:Host (host "s3")       :HostPort (port "s3")}]})

(defn- app-source []
  (:out (sh "spurious" "ports" "--json")))

(def cache-docker-source (memoize docker-source))
(def cache-app-source (memoize app-source))

(defn- parse-data [key obj]
  (first (key obj)))

(defn endpoint [type key]
  (if (= type :app)
    (parse-data key (json/read-str (cache-app-source) :key-fn keyword))
    (parse-data key (cache-docker-source))))

(def base-credentials
  {:access-key "development_access"
   :secret-key "development_secret"
   :client-config {:protocol "http"}})

(defn cred [{:keys [:Host :HostPort]}]
  (assoc base-credentials :endpoint (str Host ":" HostPort)))
