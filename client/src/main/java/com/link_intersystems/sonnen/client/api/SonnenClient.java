package com.link_intersystems.sonnen.client.api;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface SonnenClient {
    Latestdata getLatestdata() throws SonnenClientException;

    Status getStatus() throws SonnenClientException;

    <T> T getConfiguration(Configuration<T> configuration) throws SonnenClientException;
}
