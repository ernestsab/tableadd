(ns tableadd.models.item
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/tableadd"))

(defn all []
  (into [] (sql/query spec ["select * from items order by id desc limit 128"])))

(defn create [item]
  (sql/insert! spec :items [:body] [item]))
