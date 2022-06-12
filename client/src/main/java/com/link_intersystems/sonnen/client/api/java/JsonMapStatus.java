package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.Status;

import java.util.Map;

public class JsonMapStatus extends AbstractJsonMapData implements Status {

    public JsonMapStatus(Map<String, Object> properties) {
        super(properties);
    }

    @Override
    public String getBackupBuffer() {
        return (String) properties.get("BackupBuffer");
    }

    @Override
    public Boolean getBatteryCharging() {
        return (Boolean) properties.get("BatteryCharging");
    }

    @Override
    public Boolean getBatteryDischarging() {
        return (Boolean) properties.get("BatteryDischarging");
    }

    @Override
    public Integer getConsumptionAvg() {
        return (Integer) properties.get("Consumption_Avg");
    }

    @Override
    public Integer getConsumptionW() {
        return (Integer) properties.get("Consumption_W");
    }

    @Override
    public Double getFac() {
        return (Double) properties.get("Fac");
    }

    @Override
    public Boolean getFlowConsumptionBattery() {
        return (Boolean) properties.get("FlowConsumptionBattery");
    }

    @Override
    public Boolean getFlowConsumptionGrid() {
        return (Boolean) properties.get("FlowConsumptionGrid");
    }

    @Override
    public Boolean getFlowConsumptionProduction() {
        return (Boolean) properties.get("FlowConsumptionProduction");
    }

    @Override
    public Boolean getFlowGridBattery() {
        return (Boolean) properties.get("FlowGridBattery");
    }

    @Override
    public Boolean getFlowProductionBattery() {
        return (Boolean) properties.get("FlowProductionBattery");
    }

    @Override
    public Boolean getFlowProductionGrid() {
        return (Boolean) properties.get("FlowProductionGrid");
    }

    @Override
    public Integer getGridFeedInW() {
        return (Integer) properties.get("GridFeedIn_W");
    }

    @Override
    public Integer getIsSystemInstalled() {
        return (Integer) properties.get("IsSystemInstalled");
    }

    @Override
    public String getOperatingMode() {
        return (String) properties.get("OperatingMode");
    }

    @Override
    public Integer getPacTotalW() {
        return (Integer) properties.get("Pac_total_W");
    }

    @Override
    public Integer getProductionW() {
        return (Integer) properties.get("Production_W");
    }

    @Override
    public Integer getRsoc() {
        return (Integer) properties.get("RSOC");
    }

    @Override
    public Integer getRemainingCapacityWh() {
        return (Integer) properties.get("RemainingCapacity_Wh");
    }

    @Override
    public String getSystemStatus() {
        return (String) properties.get("SystemStatus");
    }

    @Override
    public String getTimestamp() {
        return (String) properties.get("Timestamp");
    }

    @Override
    public Integer getUsoc() {
        return (Integer) properties.get("USOC");
    }

    @Override
    public Integer getUac() {
        return (Integer) properties.get("Uac");
    }

    @Override
    public Integer getUbat() {
        return (Integer) properties.get("Ubat");
    }

    @Override
    public Boolean getDischargeNotAllowed() {
        return (Boolean) properties.get("dischargeNotAllowed");
    }

    @Override
    public Boolean getGeneratorAutostart() {
        return (Boolean) properties.get("generator_autostart");
    }
}
