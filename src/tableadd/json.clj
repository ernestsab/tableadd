(ns tableadd.json
  (:require [clojure.data.json :as json]
            [tableadd.models.item :as item]))

(def maps-vector (json/read-str (slurp "http://pharmit.daanstreng.nl/")))
(def first-name-values (into [] (map #(get % "FirstName") maps-vector)))
(def last-name-values (into [] (map #(get % "LastName") maps-vector)))
(def phone-values (into [] (map #(get % "PhoneNumber") maps-vector)))
(def kenmerk-values (into [] (map #(get % "KenmerkGebruiker") maps-vector)))

(def first-name-obj
  (.createArrayOf item/spec "varchar" (into-array String first-name-values)))

(defn create-task [task-name]
  (sql/insert! item/spec
               :items [:first_name] [task-name])
  (println (str "Adding task " task-name " right now")))