package com.link_intersystems.sonnen.client.api;

import com.link_intersystems.sonnen.client.api.java.*;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface IcStatus {
    JsonDCShutdownReason getDCShutdownReason();

    JsonEclipseLed getEclipseLed();

    JsonMISCStatusBits getMISCStatusBits();

    JsonMicrogridStatus getMicrogridStatus();

    JsonSetpointPriority getSetpointPriority();

    JsonSystemValidation getSystemValidation();

    Integer getNrbatterymodules();

    Integer getSecondssincefullcharge();

    String getStatebms();

    String getStatecorecontrolmodule();

    String getStateinverter();

    String getTimestamp();
}
