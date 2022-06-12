package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.IcStatus;

import java.util.Map;
import java.util.function.Function;

public class JsonIcStatus implements IcStatus {

    private Map<String, Object> properties;

    public JsonIcStatus(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public JsonDCShutdownReason getDCShutdownReason() {
        return getObject("DC Shutdown Reason", JsonDCShutdownReason::new);
    }

    @SuppressWarnings("unchecked")
    private <T> T getObject(String name, Function<Map<String, Object>, T> constructor) {
        Map<String, Object> jsonData = (Map<String, Object>) properties.get(name);
        if (jsonData != null) {
            return constructor.apply(jsonData);
        }
        return null;
    }

    @Override
    public JsonEclipseLed getEclipseLed() {
        return getObject("Eclipse Led", JsonEclipseLed::new);
    }

    @Override
    public JsonMISCStatusBits getMISCStatusBits() {
        return getObject("MISC Status Bits", JsonMISCStatusBits::new);
    }

    @Override
    public JsonMicrogridStatus getMicrogridStatus() {
        return getObject("Microgrid Status", JsonMicrogridStatus::new);
    }

    @Override
    public JsonSetpointPriority getSetpointPriority() {
        return getObject("Setpoint Priority", JsonSetpointPriority::new);
    }

    @Override
    public JsonSystemValidation getSystemValidation() {
        return getObject("System Validation", JsonSystemValidation::new);
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
