package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.EclipseLed;

import java.util.Map;

public class JsonMapEclipseLed implements EclipseLed {

    private Map<String, Object> properties;

    public JsonMapEclipseLed(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public Boolean getBlinkingRed() {
        return (Boolean) properties.get("Blinking Red");
    }

    @Override
    public Boolean getPulsingGreen() {
        return (Boolean) properties.get("Pulsing Green");
    }

    @Override
    public Boolean getPulsingOrange() {
        return (Boolean) properties.get("Pulsing Orange");
    }

    @Override
    public Boolean getPulsingWhite() {
        return (Boolean) properties.get("Pulsing White");
    }

    @Override
    public Boolean getSolidRed() {
        return (Boolean) properties.get("Solid Red");
    }
}
