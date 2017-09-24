(ns tableadd.json
  (:require [clojure.data.json :as json]
            [tableadd.models.item :as item]
            [clojure.java.jdbc :as sql]))

 (def con (sql/get-connection item/spec))

; (extend-protocol clojure.java.jdbc/ISQLParameter
;   clojure.lang.IPersistentVector
;   (set-parameter [v ^java.sql.PreparedStatement stmt ^long i]
;     (let [con (.getConnection stmt)
;           meta (.getParameterMetaData stmt)
;           type-name (.getParameterTypeName meta i)]
;       (if-let [elem-type (when (= (first type-name) \_) (apply str (rest type-name)))]
;         (.setObject stmt i (.createArrayOf con elem-type (to-array v)))
;         (.setObject stmt i v)))))

; (extend-protocol clojure.java.jdbc/IResultSetReadColumn
;   java.sql.Array
;   (result-set-read-column [val _ _]
;     (into [] (.getArray val))))

(def maps-vector (json/read-str (slurp "http://pharmit.daanstreng.nl/") :key-fn keyword))

(def json-items
      (vec (map vec (map vals maps-vector))))

(defn json-insert []
 (sql/insert-multi! item/spec :items 
        [:first_name :last_name :phone_number :kenmerk_gebruiker] json-items))