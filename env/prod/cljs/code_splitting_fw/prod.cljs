(ns code-splitting-fw.prod
  (:require [code-splitting-fw.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
