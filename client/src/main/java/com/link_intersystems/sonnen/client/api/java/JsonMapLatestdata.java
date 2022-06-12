package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.Latestdata;

import java.util.Map;

public class JsonMapLatestdata extends AbstractJsonMapData implements Latestdata {

    public JsonMapLatestdata(Map<String, Object> properties) {
        super(properties);
    }

    @Override
    public Integer getConsumptionW() {
        return (Integer) properties.get("Consumption_W");
    }

    @Override
    public Integer getFullChargeCapacity() {
        return (Integer) properties.get("FullChargeCapacity");
    }

    @Override
    public Integer getGridFeedInW() {
        return (Integer) properties.get("GridFeedIn_W");
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
    public Integer getSetPointW() {
        return (Integer) properties.get("SetPoint_W");
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
    public Integer getUTCOffet() {
        return (Integer) properties.get("UTC_Offet");
    }

    @Override
    public JsonMapIcStatus getIcStatus() {
        return (JsonMapIcStatus) properties.get("ic_status");
    }
}
