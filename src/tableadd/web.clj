(ns tableadd.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [tableadd.controllers.items :as items]
            [tableadd.views.layout :as layout]
            [tableadd.models.migration :as schema]
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
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))] (start port))
  (apply sql/insert! item/spec :items 
         [:first_name :last_name :phone_number :kenmerk_gebruiker]
         ["Thorax" "TestgebruikerZvJ1" "033-4659115" "3097"] 
         ["Test" "Wolfpacktest1" "01010101" "3099"] 
         ["Test" "Wolfpacktest3" "90340909" "3101"] 
         ["Test" "Wolfpacktest2" "01010101" "3100"] 
         ["Test" "Wolfpacktest5" "0101010010101" "3103"] 
         ["Test" "Wolfpacktest4" "0101010010101" "3102"]
         ["Test" "Wolfpacktest8" "010101001011100" "3106"] 
         ["Test" "Wolfpacktest6" "01010101010010101" "3104"] 
         ["Test" "Wolfpacktest7" "1010101010" "3105"] 
         ["Thorax" "TestgebruikerZvJ2" "033 465 91 15" "3095"] 
         ["Thorax" "TestgebruikerZvJBeheer" "033-46591152" "3094"]))
                               
  