(ns tableadd.models.migration
  (:require [clojure.java.jdbc :as sql]
            [tableadd.models.item :as item]))

(defn migrated? []
  (-> (sql/query item/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='items'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "making table and it crashes......") (flush)
    (sql/db-do-commands item/spec
                        (sql/create-table-ddl 
                        :items
                         [[:id :serial "PRIMARY KEY"]
                         [:first_name :varchar]
                         [:last_name :varchar]
                         [:phone_number :varchar]
                         [:kenmerk_gebruiker :varchar]
                         [:created_at :timestamp "DEFAULT CURRENT_TIMESTAMP"]]))
    (println " done")))