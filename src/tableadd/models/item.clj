(ns tableadd.models.item
  (:require [clojure.java.jdbc :as sql]))

; (def spec (or (System/getenv "DATABASE_URL")
;               "postgresql://localhost:5432/tableadd"))

(def spec {:subprotocol "postgresql"
               :subname "//ec2-54-221-221-153.compute-1.amazonaws.com:5432/da62bk7qc0r3o3"
               :user "cefgxbodvolovf"
               :password "6f31bcb812332f5547c56d2235a8a2d84c220cea66d4eb59dafe6416f1cca1dd"
               :ssl true
               :sslmode true
               :sslfactory "org.postgresql.ssl.NonValidatingFactory"})

(defn all []
  (into [] (sql/query spec ["select * from items order by id desc limit 128"])))

(defn create [item]
  (sql/insert! spec :items [:first_name :last_name :phone_number :kenmerk_gebruiker]
                           [item item item item]))

			  