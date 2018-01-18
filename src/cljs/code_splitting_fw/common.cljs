(ns code-splitting-fw.common
  (:require [reagent.core :as r]))

(defonce common-atom (r/atom 0))

(defn clicks [button-label]
  [:div
   [:h1 @common-atom " Clicks!"]
   [:button {:on-click #(swap! common-atom inc)}
    button-label]])

