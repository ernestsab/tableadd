(ns tableadd.json
  (:require [clojure.data.json :as json]
            [tableadd.models.item :as item]
            [clojure.java.jdbc :as sql]))

; (def con (sql/get-connection item/spec))

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
(def first-name-values (into [] (map #(get % "FirstName") maps-vector)))
(def last-name-values (into [] (map #(get % "LastName") maps-vector)))
(def phone-values (into [] (map #(get % "PhoneNumber") maps-vector)))
(def kenmerk-values (into [] (map #(get % "KenmerkGebruiker") maps-vector)))



; (def first-name-obj (.createArrayOf con "varchar" (into-array String first-name-values)))
; (def last-name-obj (.createArrayOf con "varchar" (into-array String last-name-values)))
; (def phone-obj (.createArrayOf con "varchar" (into-array String phone-values)))
; (def kenmerk-obj (.createArrayOf con "varchar" (into-array String kenmerk-values)))


(def args-list '(["Thorax" "TestgebruikerZvJ1" "033-4659115" "3097"] 
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

;(defn create-task [task-name]
 ; (sql/insert! item/spec
  ;             :items [:first_name] [task-name])
  ;(println (str "Adding task " task-name " right now")))
  
(def rows (repeat 10 ["Thorax" "TestgebruikerZvJ1" "033-4659115" "3097"]))


(defn json-insert []
  (sql/insert! item/spec :items 
      [:first_name :last_name :phone_number :kenmerk_gebruiker] ["Thorax" "TestgebruikerZvJBeheer" "033-46591152" "3094"]))
