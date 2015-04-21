(ns tutorial1.producers.file
  (:require [schema.core :as s]
            [clojure.data.json :as json]
            [clojure.java.io :as io]))

;; your code goes here


(defn load-quips
  [file]
  (if (.exists (io/as-file file))
    (:quips (json/read-str (slurp file) :key-fn keyword))
    []))

(defn save-quips
  [file data]
  (spit file (json/write-str {:quips data}))
  {:quips data})
