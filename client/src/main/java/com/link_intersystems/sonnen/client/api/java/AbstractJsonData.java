package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.JsonData;

import java.util.Map;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class AbstractJsonData implements JsonData {

    private final Map<String, Object> jsonData;

    private String json;

    public AbstractJsonData(Map<String, Object> jsonData, String json) {
        this.jsonData = jsonData;
        this.json = json;
    }

    public AbstractJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getProperty(String propertyName) {
        return (T) jsonData.get(propertyName);
    }

    @Override
    public String toString() {
        return new JsonFormatter(this).toString();
    }

    @Override
    public String getJson() {
        return json;
    }
}
