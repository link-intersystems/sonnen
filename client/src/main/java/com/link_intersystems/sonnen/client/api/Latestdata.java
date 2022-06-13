package com.link_intersystems.sonnen.client.api;

import com.link_intersystems.sonnen.client.api.java.JsonIcStatus;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface Latestdata extends JsonData, EnergyData {

    Integer getFullChargeCapacity();

    Integer getSetPointW();

    String getTimestamp();

    Integer getUTCOffet();

    JsonIcStatus getIcStatus();
}
