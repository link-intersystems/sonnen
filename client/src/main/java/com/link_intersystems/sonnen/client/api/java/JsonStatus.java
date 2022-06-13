package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.Status;

import java.util.Map;

public class JsonStatus extends AbstractJsonData implements Status {

    public JsonStatus(Map<String, Object> jsonData, String json) {
        super(jsonData, json);
    }

    @Override
    public Integer getBackupBuffer() {
        String backupBuffer = getProperty("BackupBuffer");
        if (backupBuffer == null) {
            return null;
        }
        return Integer.parseInt(backupBuffer);
    }

    @Override
    public Boolean getBatteryCharging() {
        return getProperty("BatteryCharging");
    }

    @Override
    public Boolean getBatteryDischarging() {
        return getProperty("BatteryDischarging");
    }

    @Override
    public Integer getConsumptionAvg() {
        return getProperty("Consumption_Avg");
    }

    @Override
    public Integer getConsumptionW() {
        return getProperty("Consumption_W");
    }

    @Override
    public Double getFac() {
        return getProperty("Fac");
    }

    @Override
    public Boolean getFlowConsumptionBattery() {
        return getProperty("FlowConsumptionBattery");
    }

    @Override
    public Boolean getFlowConsumptionGrid() {
        return getProperty("FlowConsumptionGrid");
    }

    @Override
    public Boolean getFlowConsumptionProduction() {
        return getProperty("FlowConsumptionProduction");
    }

    @Override
    public Boolean getFlowGridBattery() {
        return getProperty("FlowGridBattery");
    }

    @Override
    public Boolean getFlowProductionBattery() {
        return getProperty("FlowProductionBattery");
    }

    @Override
    public Boolean getFlowProductionGrid() {
        return getProperty("FlowProductionGrid");
    }

    @Override
    public Integer getGridFeedInW() {
        return getProperty("GridFeedIn_W");
    }

    @Override
    public Integer getIsSystemInstalled() {
        return getProperty("IsSystemInstalled");
    }

    @Override
    public String getOperatingMode() {
        return getProperty("OperatingMode");
    }

    @Override
    public Integer getPacTotalW() {
        return getProperty("Pac_total_W");
    }

    @Override
    public Integer getProductionW() {
        return getProperty("Production_W");
    }

    @Override
    public Integer getRsoc() {
        return getProperty("RSOC");
    }

    @Override
    public Integer getRemainingCapacityWh() {
        return getProperty("RemainingCapacity_Wh");
    }

    @Override
    public String getSystemStatus() {
        return getProperty("SystemStatus");
    }

    @Override
    public String getTimestamp() {
        return getProperty("Timestamp");
    }

    @Override
    public Integer getUsoc() {
        return getProperty("USOC");
    }

    @Override
    public Integer getUac() {
        return getProperty("Uac");
    }

    @Override
    public Integer getUbat() {
        return getProperty("Ubat");
    }

    @Override
    public Boolean getDischargeNotAllowed() {
        return getProperty("dischargeNotAllowed");
    }

    @Override
    public Boolean getGeneratorAutostart() {
        return getProperty("generator_autostart");
    }
}
