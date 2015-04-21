(ns tutorial1.consumers.web
  (:require [schema.core :as s]
            [org.httpkit.server :as server]
            [compojure
             [core :refer :all]
             [handler :as handler]
             [route :as route]]
            [ring.middleware.json :as json]
            [tutorial1.producers.file :as file]
            [tutorial1.handlers.quips :as quips])
  (:import com.fasterxml.jackson.core.JsonGenerationException))

(defn gulp-errors
  [handler]
  (fn [req]
    (try
      (handler req)
      (catch JsonGenerationException e
        {:status 500 :body {:error "Unknown error occurred"}})
      (catch Exception e
        {:status 500 :body {:error (str e)}}))))

(defn api-routes [file]
  (routes
   (GET "/quips" req
     (let [quips (quips/find file)]
       {:status 200 :body {:quips quips}}))
   (GET "/quips/random" req
     (let [quip (quips/random file)]
       {:status 200 :body quip}))
   (GET "/quips/count" req
     (let [quip-count (quips/count-quips file)]
       {:status 200 :body {:count quip-count}}))
   (POST "/quips" req
     (let [quips (quips/save req file)]
       {:status 201 :body quips}))
   (DELETE "/quips" req
     (let [quips (quips/delete-all file)]
       {:status 204}))))

(defn app [file]
  (-> (api-routes file)
      handler/api
      (json/wrap-json-body {:keywords? true})
      gulp-errors
      json/wrap-json-response))

(s/defn start
  [port :- s/Int
   file :- s/Str]
  (server/run-server (app file) {:port port}))
