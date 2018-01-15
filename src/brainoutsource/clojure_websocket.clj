(ns brainoutsource.clojure-websocket
  (:require [cheshire.core :refer :all])
  (:import (org.java_websocket.client WebSocketClient)
           (java.net URI)
           (org.java_websocket WebSocket)
           (java.nio ByteBuffer)))

(defn connect ^WebSocketClient [url & {:keys [on-open on-message on-close on-error]
               :or {on-open    (constantly nil)
                    on-message (constantly nil)
                    on-close   (constantly nil)
                    on-error   (constantly nil)}}]
  (let [client (proxy [WebSocketClient] [(URI. url)]
               (onOpen [handshake] (on-open client handshake))
               (onMessage [msg] (on-message msg))
               (onClose [code msg closed-by-server] (on-close code msg closed-by-server))
               (onError [ex] (on-error ex)))
        _      (.connect client)]
    client))

(defn close [^WebSocketClient client code]
  (.close client code))

(defn send-msg [^WebSocket client ^String msg]
  (.send client msg))

(defn send-buffer [^WebSocket client ^ByteBuffer buf]
  (.send client buf))

(defn send-bytes [^WebSocket client bytes]
  (.send client bytes))