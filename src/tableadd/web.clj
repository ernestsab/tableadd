(ns tableadd.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [tableadd.controllers.items :as items]
            [tableadd.views.layout :as layout]
            [tableadd.models.maketable :as schema]
            [clojure.java.jdbc :as sql]
            [tableadd.models.item :as item]
            [tableadd.json :as json])
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
  (schema/make)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))] (start port)))
                               
  