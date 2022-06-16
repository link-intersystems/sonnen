package com.link_intersystems.sonnen.datalogger.entity;

import com.link_intersystems.sonnen.client.api.Status;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface SonnenRepository {
    void persist(Status status);
}
