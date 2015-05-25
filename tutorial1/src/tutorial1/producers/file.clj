(ns tutorial1.producers.file
  (:require [schema.core :as s]
            [clojure.java.io :as io]
            [clojure.data.json :as json]))

(defn save-quips
  [file data]
  (spit file (json/write-str data)))

(defn load-quips
  [file]
  (if (.exists (io/as-file file))
    (let [data (slurp file)]
      (if (seq data)
        (json/read-str data)
        []))
    []))
