# yfinance2

Clojure app to download yahoo finance data. Modified from ghoseb's project yfinance, using http-kit and paralellization.

## Installation

Download from http://example.com/FIXME.

## Usage

FIXME: explanation

    $ java -jar yfinance2-0.1.0-standalone.jar [args]

## Options

FIXME: listing of options this app accepts.

## Examples
;;easy
(def ticlist '("Y" "ISRG" "CABO" "UHAL" "NEU" "BH" "ALX" "ATRI" "BLK" "MTD" "ADS" "EQIX" "AGN" "BIIB" "ORLY" "NWLI" "SHW" "FCNCA" "PCP" "ICE") )
;; to download
(def out (fetch-historical-data-group-big "2014-10-14" "2015-10-17" ticlist))
### Bugs

...

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
