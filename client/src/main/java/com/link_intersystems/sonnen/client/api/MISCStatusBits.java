package com.link_intersystems.sonnen.client.api;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface MISCStatusBits {
    Boolean getDischargeNotAllowed();

    Boolean getF1Open();

    Boolean getMinSystemSOC();

    Boolean getMinUserSOC();

    Boolean getSetpointTimeout();
}
