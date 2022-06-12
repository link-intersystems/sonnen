package com.link_intersystems.sonnen.client.api;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface Status extends JsonData {

    String getBackupBuffer();

    Boolean getBatteryCharging();

    Boolean getBatteryDischarging();

    Integer getConsumptionAvg();

    Integer getConsumptionW();

    Double getFac();

    Boolean getFlowConsumptionBattery();

    Boolean getFlowConsumptionGrid();

    Boolean getFlowConsumptionProduction();

    Boolean getFlowGridBattery();

    Boolean getFlowProductionBattery();

    Boolean getFlowProductionGrid();

    Integer getGridFeedInW();

    Integer getIsSystemInstalled();

    String getOperatingMode();

    Integer getPacTotalW();

    Integer getProductionW();

    Integer getRsoc();

    Integer getRemainingCapacityWh();

    String getSystemStatus();

    String getTimestamp();

    Integer getUsoc();

    Integer getUac();

    Integer getUbat();

    Boolean getDischargeNotAllowed();

    Boolean getGeneratorAutostart();

}
