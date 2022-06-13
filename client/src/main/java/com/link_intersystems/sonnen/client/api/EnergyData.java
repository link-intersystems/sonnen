package com.link_intersystems.sonnen.client.api;

/**
 * Common energy data.
 *
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface EnergyData {
    /**
     * @return the house consumption in watts, direct measurement.
     */
    Integer getConsumptionW();

    /**
     * @return the grid Feed in. Negative is consumption and positive is feed in.
     */
    Integer getGridFeedInW();

    /**
     * @return the AC Power greater than ZERO is discharging Inverter AC Power less than ZERO is charging.
     */
    Integer getPacTotalW();

    /**
     * @return the PV production in watts.
     */
    Integer getProductionW();

    /**
     * @return the relative state of charge.
     */
    Integer getRsoc();

    /**
     * @return the user state of charge in percent. This is usually the battery capacity that a user will see
     * in the my.sonnen.de web frontend.
     */
    Integer getUsoc();
}
