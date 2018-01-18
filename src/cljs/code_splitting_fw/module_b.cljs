(ns code-splitting-fw.module-b
  (:require
   [code-splitting-fw.common :refer [clicks]]
   [reagent.core :as reagent :refer [atom]]))

(defn home-page []
  [:div [:h2 "Welcome to module-b"]
   [clicks "Module b"]
   [:div [:a {:href "/a"} "go to module-a"]]])

(defn mount-root
  "This function is called from dev.b every time js reloads,
  because :on-jsload isn't available for modules."
  []
  (println "I am called every time module B is changed if b.js is required")
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init!
  "This function is called exactly once per b.js load because it is wrapped in a defonce"
  []
  (println "I am called once when b.js is loaded")
  (mount-root))


