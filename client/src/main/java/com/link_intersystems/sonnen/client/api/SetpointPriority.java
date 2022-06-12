package com.link_intersystems.sonnen.client.api;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface SetpointPriority {
    Boolean getBms();

    Boolean getEnergyManager();

    Boolean getFullChargeRequest();

    Boolean getInverter();

    Boolean getMinUserSOC();

    Boolean getTrickleCharge();
}
