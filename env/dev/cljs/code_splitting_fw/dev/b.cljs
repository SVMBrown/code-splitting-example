(ns code-splitting-fw.dev.b
  (:require [code-splitting-fw.dev.common]
            [code-splitting-fw.module-b :as b]))

(declare first-load?)

(when-not first-load? (b/mount-root))

(defonce first-load? (do
                       (b/init!)
                       false))

