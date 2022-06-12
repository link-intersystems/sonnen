package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.Powermeter;

import java.util.Map;

public class JsonMapPowermeter extends AbstractJsonMapData implements Powermeter {

    public JsonMapPowermeter(Map<String, Object> properties) {
        super(properties);
    }

    @Override
    public Number getaL1() {
        return (Number) properties.get("a_l1");
    }

    @Override
    public Number getaL2() {
        return (Number) properties.get("a_l2");
    }

    @Override
    public Number getaL3() {
        return (Number) properties.get("a_l3");
    }

    @Override
    public Number getChannel() {
        return (Number) properties.get("channel");
    }

    @Override
    public Number getDeviceid() {
        return (Number) properties.get("deviceid");
    }

    @Override
    public String getDirection() {
        return (String) properties.get("direction");
    }

    @Override
    public Number getError() {
        return (Number) properties.get("error");
    }

    @Override
    public Number getKwhExported() {
        return (Number) properties.get("kwh_exported");
    }

    @Override
    public Number getKwhImported() {
        return (Number) properties.get("kwh_imported");
    }

    @Override
    public Number getvL1L2() {
        return (Number) properties.get("v_l1_l2");
    }

    @Override
    public Number getvL1N() {
        return (Number) properties.get("v_l1_n");
    }

    @Override
    public Number getvL2L3() {
        return (Number) properties.get("v_l2_l3");
    }

    @Override
    public Number getvL2N() {
        return (Number) properties.get("v_l2_n");
    }

    @Override
    public Number getvL3L1() {
        return (Number) properties.get("v_l3_l1");
    }

    @Override
    public Number getvL3N() {
        return (Number) properties.get("v_l3_n");
    }

    @Override
    public Number getVaTotal() {
        return (Number) properties.get("va_total");
    }

    @Override
    public Number getVarTotal() {
        return (Number) properties.get("var_total");
    }

    @Override
    public Number getwL1() {
        return (Number) properties.get("w_l1");
    }

    @Override
    public Number getwL2() {
        return (Number) properties.get("w_l2");
    }

    @Override
    public Number getwL3() {
        return (Number) properties.get("w_l3");
    }

    @Override
    public Number getwTotal() {
        return (Number) properties.get("w_total");
    }

}
