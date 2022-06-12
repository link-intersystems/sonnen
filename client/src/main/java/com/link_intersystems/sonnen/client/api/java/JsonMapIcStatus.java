package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.IcStatus;

import java.util.Map;

public class JsonMapIcStatus implements IcStatus {

    private Map<String, Object> properties;

    public JsonMapIcStatus(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public JsonMapDCShutdownReason getDCShutdownReason() {
        return (JsonMapDCShutdownReason) properties.get("DC Shutdown Reason");
    }

    @Override
    public JsonMapEclipseLed getEclipseLed() {
        return (JsonMapEclipseLed) properties.get("Eclipse Led");
    }

    @Override
    public JsonMapMISCStatusBits getMISCStatusBits() {
        return (JsonMapMISCStatusBits) properties.get("MISC Status Bits");
    }

    @Override
    public JsonMapMicrogridStatus getMicrogridStatus() {
        return (JsonMapMicrogridStatus) properties.get("Microgrid Status");
    }


    @Override
    public JsonMapSetpointPriority getSetpointPriority() {
        return (JsonMapSetpointPriority) properties.get("Setpoint Priority");
    }

    @Override
    public JsonMapSystemValidation getSystemValidation() {
        return (JsonMapSystemValidation) properties.get("System Validation");
    }

    @Override
    public Integer getNrbatterymodules() {
        return (Integer) properties.get("nrbatterymodules");
    }

    @Override
    public Integer getSecondssincefullcharge() {
        return (Integer) properties.get("secondssincefullcharge");
    }

    @Override
    public String getStatebms() {
        return (String) properties.get("statebms");
    }


    @Override
    public String getStatecorecontrolmodule() {
        return (String) properties.get("statecorecontrolmodule");
    }

    @Override
    public String getStateinverter() {
        return (String) properties.get("stateinverter");
    }

    @Override
    public String getTimestamp() {
        return (String) properties.get("timestamp");
    }
}
