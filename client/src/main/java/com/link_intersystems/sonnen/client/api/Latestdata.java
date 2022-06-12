package com.link_intersystems.sonnen.client.api;

import com.link_intersystems.sonnen.client.api.java.JsonIcStatus;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface Latestdata extends JsonData {
    Integer getConsumptionW();

    Integer getFullChargeCapacity();

    Integer getGridFeedInW();

    Integer getPacTotalW();

    Integer getProductionW();

    Integer getRsoc();

    Integer getSetPointW();

    String getTimestamp();

    Integer getUsoc();

    Integer getUTCOffet();

    JsonIcStatus getIcStatus();
}
