(ns tableadd.controllers.items
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [tableadd.views.items :as view]
            [tableadd.models.item :as model]))

(defn index []
  (view/index (model/all)))

(defn create
  [item]
  (when-not (or (str/blank? item)
                (> (count item) 512))
    (model/create item))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" [item] (create item)))
