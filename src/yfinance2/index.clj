(ns yfinance2.index
  (:require [clj-time.core :as t]
            [clj-http.client :as client]
            [org.httpkit.client :as http]
            [clojure.string :as str]
            [incanter [core :refer [$]
                             :as incanter$]
                       [core :as incanter]
                       [stats :as stats]
                       [io :as io2]
                       [charts :as charts]
                       [datasets :as dataset]
             ]


            )

  (:gen-class)
  )


(def #^{:private true} +base-url+ "http://itable.finance.yahoo.com/table.csv?s=%s&g=d&a=%d&b=%d&c=%d&d=%d&e=%d&f=%d")

(defn- get-full-url
  "Construct the complete URL given the params"
  [y1 m1 d1 y2 m2 d2 sym]
  (let [start (t/date-time y1 m1 d1)
        end (t/date-time y2 m2 d2)]
    (format +base-url+
            sym
            (dec (t/month start))
            (t/day start)
            (t/year start)
            (dec (t/month end))
            (t/day end)
            (t/year end))))

(defn- fetch-url
  "Fetch one URL using HTTP-KIT"
  [url]

  @(http/get url) ;; forced http agent promise to block

  )

(defn- collect-response
  "Wait for all the agents to finish and then return the response"
  [& agnts]
  ;(apply await agnts)                   ; no more agents
  (for [a agnts]
    (if (= (:status a) 200)
      (:body a)
      (:status a))))

(defn fetch-historical-data
  "Fetch historical prices from Yahoo! finance for the given symbol between start and end"
  [start end sym]
  (letfn [(parse-date [dt] (map #(Integer/parseInt %) (.split dt "-")))]
    (let [[y1 m1 d1] (parse-date start)
          [y2 m2 d2] (parse-date end)
          url (get-full-url y1 m1 d1 y2 m2 d2 sym)
          agnt (fetch-url url)]
    (vector sym (first (collect-response agnt )))
      )
    ))


(defn fetch-historical-data-group-big[start end syms]
"Parallelize the fetch data function using pmap, but restrict the number of threads"
  (letfn [ (fetch-historical-data-group [start1 end1 syms1] (map (partial fetch-historical-data start1 end1) syms1)) ]
(let [data (pmap (partial fetch-historical-data-group start end) (partition-all 10 syms))
      data2 (apply concat data)
      ](identity data2))
))


(defn -main[& args]
;;dummy example
(def ticlist '("Y" "ISRG" "CABO" "UHAL" "NEU" "BH" "ALX" "ATRI" "BLK" "MTD" "ADS" "EQIX" "AGN" "BIIB" "ORLY" "NWLI" "SHW" "FCNCA" "PCP" "ICE") )
;; to get to a price
(def out (fetch-historical-data-group-big "2014-10-14" "2015-10-17" ticlist))
(println (map #(read-string (nth (str/split % #"," ) 6)) (rest (str/split (second (second out)) #"\n"))))
)











