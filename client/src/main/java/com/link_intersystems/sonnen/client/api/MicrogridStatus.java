package com.link_intersystems.sonnen.client.api;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface MicrogridStatus {
    Boolean getContiniousPowerViolation();

    Boolean getDischargeCurrentLimitViolation();

    Boolean getLowTemperature();

    Boolean getMaxSystemSOC();

    Boolean getMaxUserSOC();

    Boolean getMicrogridEnabled();

    Boolean getMinSystemSOC();

    Boolean getMinUserSOC();

    Boolean getOverChargeCurrent();

    Boolean getOverDischargeCurrent();

    Boolean getPeakPowerViolation();

    Boolean getProtectIsActivated();

    Boolean getTransitionToOngridPending();
}
