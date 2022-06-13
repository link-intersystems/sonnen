package com.link_intersystems.sonnen.client.api;

import java.util.List;

/**
 * A client to manage a sonnenBatterie.  Compatible with sonnen software 1.6.24.
 *
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface SonnenClient {
    /**
     * @return the latest data for this sonnenBatterie.
     * @throws SonnenClientException in case of any exception.
     */
    Latestdata getLatestdata() throws SonnenClientException;

    /**
     * Gets status data for the sonnenBatterie.
     *
     * @return the status data.
     * @throws SonnenClientException in case of any exception.
     */
    Status getStatus() throws SonnenClientException;

    /**
     * Gets the value for one of these configurations:
     * <ul>
     *      <li>{@link Configuration#CM_MarketingModuleCapacity}</li>
     *      <li>{@link Configuration#DE_Software}</li>
     *      <li>{@link Configuration#EM_OperatingMode}</li>
     *      <li>{@link Configuration#EM_ToU_Schedule}</li>
     *      <li>{@link Configuration#EM_USOC}</li>
     *      <li>{@link Configuration#EM_US_CHP_Max_SOC}</li>
     *      <li>{@link Configuration#EM_US_CHP_Min_SOC}</li>
     *      <li>{@link Configuration#EM_US_GENRATOR_TYPE}</li>
     *      <li>{@link Configuration#EM_US_GEN_POWER_SET_POINT}</li>
     *      <li>{@link Configuration#EM_US_RE_ENABLE_MICROGRID}</li>
     *      <li>{@link Configuration#EM_US_USER_INPUT_TIME_ONE}</li>
     *      <li>{@link Configuration#EM_US_USER_INPUT_TIME_THREE}</li>
     *      <li>{@link Configuration#EM_US_USER_INPUT_TIME_TWO}</li>
     *      <li>{@link Configuration#IC_BatteryModules}</li>
     *      <li>{@link Configuration#IC_InverterMaxPower_w}</li>
     *      <li>{@link Configuration#NVM_PfcFixedCosPhi}</li>
     *      <li>{@link Configuration#NVM_PfcIsFixedCosPhiActive}</li>
     *      <li>{@link Configuration#NVM_PfcIsFixedCosPhiLagging}</li>
     * </ul>
     *
     * @param configuration the configuration to get.
     * @param <T>           the configuration value type.
     * @return the configuration value.
     * @throws SonnenClientException in case of any exception.
     */
    <T> T getConfiguration(Configuration<T> configuration) throws SonnenClientException;


    /**
     * Sets the value of these configurations:
     * <ul>
     *      <li>{@link Configuration#CM_MarketingModuleCapacity}</li>
     *      <li>{@link Configuration#DE_Software}</li>
     *      <li>{@link Configuration#EM_OperatingMode}</li>
     *      <li>{@link Configuration#EM_ToU_Schedule}</li>
     *      <li>{@link Configuration#EM_USOC}</li>
     *      <li>{@link Configuration#EM_US_CHP_Max_SOC}</li>
     *      <li>{@link Configuration#EM_US_CHP_Min_SOC}</li>
     *      <li>{@link Configuration#EM_US_GENRATOR_TYPE}</li>
     *      <li>{@link Configuration#EM_US_GEN_POWER_SET_POINT}</li>
     *      <li>{@link Configuration#EM_US_RE_ENABLE_MICROGRID}</li>
     *      <li>{@link Configuration#EM_US_USER_INPUT_TIME_ONE}</li>
     *      <li>{@link Configuration#EM_US_USER_INPUT_TIME_THREE}</li>
     *      <li>{@link Configuration#EM_US_USER_INPUT_TIME_TWO}</li>
     *      <li>{@link Configuration#IC_BatteryModules}</li>
     *      <li>{@link Configuration#IC_InverterMaxPower_w}</li>
     *      <li>{@link Configuration#NVM_PfcFixedCosPhi}</li>
     *      <li>{@link Configuration#NVM_PfcIsFixedCosPhiActive}</li>
     *      <li>{@link Configuration#NVM_PfcIsFixedCosPhiLagging}</li>
     * </ul>
     *
     * @param configuration the configuration to set.
     * @param value         the configuration value.
     * @param <T>           the configuration value type.
     * @throws SonnenClientException in case of any exception.
     */
    <T> void setConfiguration(Configuration<T> configuration, T value) throws SonnenClientException;

    /**
     * Gets the latest power-meter measurements.
     *
     * @return the latest power-meter measurements.
     * @throws SonnenClientException in case of any exception.
     */
    List<Powermeter> getPowermeter() throws SonnenClientException;

    /**
     * The discharging power of a storage system can be controlled by setting a setpoint in watts.
     * The corresponding value of the setpoint is kept until the battery receives a new charging or discharging value.
     * If VPP (Virtual Power Plant) is active, the request will be rejected.
     *
     * @param watt the discharging power of a storage system.
     * @throws SonnenClientException in case of any exception.
     * @see <a href="https://sonnen.de/haeufig-gestellte-fragen/sonnenvpp/">sonnenVPP</a>
     */
    void discharge(int watt) throws SonnenClientException;

    /**
     * The charging power of a storage system can be controlled by setting a setpoint in watts.
     * The corresponding value of the setpoint is kept until the battery receives a new charging or discharging value.
     * If VPP (Virtual Power Plant) is active, the request will be rejected.
     *
     * @param watt the charging power of a storage system.
     * @throws SonnenClientException in case of any exception.
     * @see <a href="https://sonnen.de/haeufig-gestellte-fragen/sonnenvpp/">sonnenVPP</a>
     */
    void charge(int watt) throws SonnenClientException;


}
