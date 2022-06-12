package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.SetpointPriority;

import java.util.Map;

public class JsonMapSetpointPriority implements SetpointPriority {

    private Map<String, Object> properties;

    public JsonMapSetpointPriority(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public Boolean getBms() {
        return (Boolean) properties.get("BMS");
    }

    @Override
    public Boolean getEnergyManager() {
        return (Boolean) properties.get("Energy Manager");
    }

    @Override
    public Boolean getFullChargeRequest() {
        return (Boolean) properties.get("Full Charge Request");
    }

    @Override
    public Boolean getInverter() {
        return (Boolean) properties.get("Inverter");
    }

    @Override
    public Boolean getMinUserSOC() {
        return (Boolean) properties.get("Min User SOC");
    }

    @Override
    public Boolean getTrickleCharge() {
        return (Boolean) properties.get("Trickle Charge");
    }

}
