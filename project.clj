(defproject spurious-aws-sdk-helper "0.1.0"
  :description "A Clojure helper class for configuring a Clojure AWS SDK (Amazonica) to talk to the Spurious services (https://github.com/spurious-io/spurious)"
  :url "https://github.com/Integralist/spurious-clojure-aws-sdk-helper"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.json "0.2.5"]
                 [amazonica "0.3.18"]
                 [environ "1.0.0"]
                 [circleci/clj-yaml "0.5.3"]])
