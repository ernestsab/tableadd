(ns tableadd.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [tableadd.controllers.items :as items]
            [tableadd.views.layout :as layout]
            [tableadd.models.migration :as schema]
            [tableadd.json :as json]
            [clojure.java.jdbc :as sql]
            [tableadd.models.item :as item])
  (:gen-class))

(defroutes routes
  items/routes
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))

(def application (handler/site routes))

(defn start [port]
  (ring/run-jetty application {:port port
                               :join? false}))

(defn -main []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))] (start port))
  (apply sql/insert! item/spec :items 
               ([:first_name :last_name :phone_number :kenmerk_gebruiker]
               [json/first-name-values json/last-name-values json/phone-values json/kenmerk-values]))
