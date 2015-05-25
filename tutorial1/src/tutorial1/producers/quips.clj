(ns tutorial1.producers.quips
  (:require [schema.core :as s]
            [tutorial1.producers.file :as file]))

(defn random-quip
  [file]
  (let [quips (file/load-quips file)]
    (if (seq quips)
      (assoc {} :quip (rand-nth quips))
      {})))

(defn get-all-quips
  [file]
  (let [quips (file/load-quips file)]
    (if (seq quips)
      (map #(assoc {} :quip %) quips)
      {})))

(defn count-quips
  [file]
  (let [quips (file/load-quips file)]
    (if (seq quips)
      (count quips)
      0)))

(defn update-quips
  [file req]
  (let [old-quips (file/load-quips file)
        new-quips (map :quip (get-in req [:body :quips]))]
    (file/save-quips file (into [] (concat old-quips new-quips)))))

(defn delete-all
  [file]
  (file/save-quips file []))
