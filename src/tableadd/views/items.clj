(ns tableadd.views.items
  (:require [tableadd.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]))

(defn item-form []
  [:div {:id "item-form" :class "sixteen columns alpha omega"}
   (form/form-to [:post "/"]
                 (form/label "item" "Add stuff to postgre table")
                 (form/text-area "item")
                 (form/submit-button "Add!"))])

(defn display-items [items]
  [:div {:class "items sixteen columns alpha omega"}
   (map
    (fn [item] [:h2 {:class "item"} (h (:body item))])
    items)])

(defn index [items]
  (layout/common "TABLEADD"
                 (item-form)
                 [:div {:class "clear"}]
                 (display-items items)))
