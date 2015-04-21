(defproject liveops/tutorial1 "0.1.0-SNAPSHOT"
  :description "TODO: Write a project description."
  :url "http://repository-url/FIXME"
  :profiles {:uberjar {:aot :all
                       :uberjar-name "tutorial1.jar"}}
  :dependencies [[prismatic/schema "0.4.0"]
                 [org.clojure/clojure "1.7.0-beta1"]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.clojure/data.json "0.2.6"]
                 [http-kit "2.1.19"]
                 [ring "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [compojure "1.3.3"]
                 [clj-http "1.1.0"]]
  :main tutorial1.core)
