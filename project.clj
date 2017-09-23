(defproject tableadd "0.0.1"
  :description "Add stuff in the database"
  :url ""
  :min-lein-version "2.0.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/java.jdbc "0.7.1"]
                 [postgresql "9.4-1202.jdbc4"]
                 [org.clojure/data.json "0.2.6"]
                 [mysql/mysql-connector-java "5.1.6"]
    ;;           [ring/ring-jetty-adapter "1.2.1"]
                 [ring "1.6.2"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot tableadd.web
  :uberjar-name "tableadd.jar"
  :profiles {:uberjar {:aot :all}})
