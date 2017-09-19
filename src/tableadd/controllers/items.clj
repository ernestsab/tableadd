(ns tableadd.controllers.items
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [tableadd.views.items :as view]
            [tableadd.models.item :as model]))

(defn index []
  (view/index (model/all)))

(defn create
  [entry]
  (when-not (or (str/blank? entry)
                (> (count entry) 512))
    (model/create entry))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" [entry] (create entry)))
