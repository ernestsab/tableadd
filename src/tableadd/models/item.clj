(ns tableadd.models.item
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/tableadd"))

(def postgres {:subprotocol "postgresql"
               :subname "//ec2-54-225-182-108.compute-1.amazonaws.com:5432/d990r2bpqnml11"
               :user "hbfxruceherfaq"
               :password "ffda9bbfd61a597d596edf27289d269b6565a4558c3920c8bc5b2ce9f3d6f189"
               :ssl true
               :sslmode true
               :sslfactory "org.postgresql.ssl.NonValidatingFactory"})

(defn all []
  (into [] (sql/query spec ["select * from items order by id desc limit 128"])))

(defn create [item]
  (sql/insert! spec :items [:first_name :last_name :phone_number :kenmerk_gebruiker]
                           [item item item item]))

			  