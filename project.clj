(defproject spurious-aws-sdk-helper "0.1.0-SNAPSHOT"
  :description "A Clojure helper class for configuring a Clojure AWS SDK (Amazonica) to talk to the Spurious services (https://github.com/stevenjack/spurious)"
  :url "https://github.com/Integralist/spurious-clojure-aws-sdk-helper"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [amazonica "0.3.13"]]
  :profiles {:dev {:plugins [[lein-dotenv "1.0.0"]]}})
