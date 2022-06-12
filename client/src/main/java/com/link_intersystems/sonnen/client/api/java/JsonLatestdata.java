package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.Latestdata;

import java.util.Map;

public class JsonLatestdata extends AbstractJsonData implements Latestdata {

    public JsonLatestdata(Map<String, Object> jsonData, String json) {
        super(jsonData, json);
    }

    @Override
    public Integer getConsumptionW() {
        return getProperty("Consumption_W");
    }

    @Override
    public Integer getFullChargeCapacity() {
        return getProperty("FullChargeCapacity");
    }

    @Override
    public Integer getGridFeedInW() {
        return getProperty("GridFeedIn_W");
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
    public Integer getSetPointW() {
        return getProperty("SetPoint_W");
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
    public Integer getUTCOffet() {
        return getProperty("UTC_Offet");
    }

    @Override
    public JsonIcStatus getIcStatus() {
        Map<String, Object> jsonData = getProperty("ic_status");
        if (jsonData != null) {
            return new JsonIcStatus(jsonData);
        }
        return null;
    }
}
