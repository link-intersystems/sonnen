package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.MISCStatusBits;

import java.util.Map;

public class JsonMISCStatusBits implements MISCStatusBits {


    private Map<String, Object> properties;

    public JsonMISCStatusBits(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public Boolean getDischargeNotAllowed() {
        return (Boolean) properties.get("Discharge not allowed");
    }


    @Override
    public Boolean getF1Open() {
        return (Boolean) properties.get("F1 open");
    }

    @Override
    public Boolean getMinSystemSOC() {
        return (Boolean) properties.get("Min System SOC");
    }

    @Override
    public Boolean getMinUserSOC() {
        return (Boolean) properties.get("Min User SOC");
    }

    @Override
    public Boolean getSetpointTimeout() {
        return (Boolean) properties.get("Setpoint Timeout");
    }
}
