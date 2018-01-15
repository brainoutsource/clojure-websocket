# clojure-websocket

[![Clojars Project](https://img.shields.io/clojars/v/clojure-websocket.svg)](https://clojars.org/clojure-websocket)

A clojure wrapper for [TooTallNate/Java-WebSocket](https://github.com/TooTallNate/Java-WebSocket) which is 100% Jetty-free.

## Usage

```
(def client (connect "wss://some-service/"
          :on-open    (fn [_] (println "Open"))
          :on-message (fn [msg] (println (str "Message: " msg))
          :on-close   (fn [code msg by-server] (println (str "Closed: " code "," msg "," by-server)))
          :on-error   (fn [ex] (clojure.stacktrace/print-stack-trace ex)))))

(send-msg client "some-message")
```

## License

Copyright © 2018 Alexander Eliseyev

Distributed under the Eclipse Public License, the same as Clojure.
