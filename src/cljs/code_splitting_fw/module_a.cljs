(ns code-splitting-fw.module-a
  (:require
   [code-splitting-fw.common :refer [clicks]]
   [reagent.core :as reagent :refer [atom]]))

(defn home-page []
  [:div [:h2 "Welcome to module-a"]
   [clicks "Module A"]
   [:div [:a {:href "/b"} "go to module-b"]]])

(defn mount-root
  "This function is called from dev.a every time js reloads,
  because :on-jsload isn't available for modules"
  []
  (println "I am called every time module A is changed if a.js is required")
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init!
  "This function is called exactly once per a.js load because is is wrapped in a defonce"
  []
  (println "I am called once when a.js is loaded")
  (mount-root))

