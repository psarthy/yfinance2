(defproject yfinance2 "0.1.0-SNAPSHOT"
  :description "A clojure app to fetch yahoo finance data using http-kit"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]

                 [clj-http "1.0.1"]
                 [clj-time "0.11.0"]
                 [http-kit "2.1.18"]
                 [incanter "1.5.6"]

		             ]
  :main ^:skip-aot yfinance2.index
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
