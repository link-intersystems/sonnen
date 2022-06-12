package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.Powermeter;

import java.util.Map;

public class JsonPowermeter extends AbstractJsonData implements Powermeter {

    public JsonPowermeter(Map<String, Object> jsonObject) {
        super(jsonObject);
    }

    @Override
    public Number getaL1() {
        return getProperty("a_l1");
    }

    @Override
    public Number getaL2() {
        return getProperty("a_l2");
    }

    @Override
    public Number getaL3() {
        return getProperty("a_l3");
    }

    @Override
    public Number getChannel() {
        return getProperty("channel");
    }

    @Override
    public Number getDeviceid() {
        return getProperty("deviceid");
    }

    @Override
    public String getDirection() {
        return getProperty("direction");
    }

    @Override
    public Number getError() {
        return getProperty("error");
    }

    @Override
    public Number getKwhExported() {
        return getProperty("kwh_exported");
    }

    @Override
    public Number getKwhImported() {
        return getProperty("kwh_imported");
    }

    @Override
    public Number getvL1L2() {
        return getProperty("v_l1_l2");
    }

    @Override
    public Number getvL1N() {
        return getProperty("v_l1_n");
    }

    @Override
    public Number getvL2L3() {
        return getProperty("v_l2_l3");
    }

    @Override
    public Number getvL2N() {
        return getProperty("v_l2_n");
    }

    @Override
    public Number getvL3L1() {
        return getProperty("v_l3_l1");
    }

    @Override
    public Number getvL3N() {
        return getProperty("v_l3_n");
    }

    @Override
    public Number getVaTotal() {
        return getProperty("va_total");
    }

    @Override
    public Number getVarTotal() {
        return getProperty("var_total");
    }

    @Override
    public Number getwL1() {
        return getProperty("w_l1");
    }

    @Override
    public Number getwL2() {
        return getProperty("w_l2");
    }

    @Override
    public Number getwL3() {
        return getProperty("w_l3");
    }

    @Override
    public Number getwTotal() {
        return getProperty("w_total");
    }

}
