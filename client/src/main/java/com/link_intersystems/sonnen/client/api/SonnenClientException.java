package com.link_intersystems.sonnen.client.api;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class SonnenClientException extends Exception {

    public SonnenClientException() {
    }

    public SonnenClientException(String message) {
        super(message);
    }

    public SonnenClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public SonnenClientException(Throwable cause) {
        super(cause);
    }

    public SonnenClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
