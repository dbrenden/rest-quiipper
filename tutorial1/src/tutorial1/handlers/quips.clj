(ns tutorial1.handlers.quips
  (:refer-clojure :exclude [find])
  (:require [tutorial1.producers.file :as quip-file]))

(defn find
  [file]
  (quip-file/load-quips file))

(defn save
  [req file]
  (let [saved-quips (quip-file/load-quips file) new-quips (:quips (:body req))]
    (quip-file/save-quips file (distinct (into saved-quips new-quips)))))

(defn random
  [file]
  (let [quips (quip-file/load-quips file)]
    (if (empty? quips)
      {}
      (rand-nth quips))))

(defn count-quips
  [file]
  (count (quip-file/load-quips file)))

(defn delete-all
  [file]
  (quip-file/save-quips file []))
