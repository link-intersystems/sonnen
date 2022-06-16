package com.link_intersystems.sonnen.datalogger.gateway;

import java.time.ZoneId;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface MongoDBConfiguration {

    public String getStatusCollectionName();

    ZoneId getTimeZoneId();
}
