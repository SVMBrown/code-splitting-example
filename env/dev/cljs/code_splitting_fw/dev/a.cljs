(ns code-splitting-fw.dev.a
  (:require [code-splitting-fw.dev.common]
            [code-splitting-fw.module-a :as a]))

;; hack around on-jsload.
;; takes advantage of unbound variables being truthy to call mount-root only if init! has already been called.

(declare first-load?)

(when-not first-load? (a/mount-root))

(defonce first-load? (do
                       (a/init!)
                       false))

