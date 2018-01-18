(ns code-splitting-fw.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [code-splitting-fw.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]))

(def mount-target
  [:div#app
      [:h3 "ClojureScript has not been compiled!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))])

(defn loading-page [script]
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "js/out/cljs_base.js")
     (include-js script)]))


(defroutes routes
  (GET "/" [] (html5 (head) [:body [:ul
                                    [:li [:a {:href "/a"} "module a"]]
                                    [:li [:a {:href "/b"} "module b"]]]]))
  (GET "/a" [] (loading-page "js/out/a.js"))
  (GET "/b" [] (loading-page "js/out/b.js"))
  (resources "/")
  (not-found "Not Found"))

(def app (wrap-middleware #'routes))
