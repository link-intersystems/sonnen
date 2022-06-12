package com.link_intersystems.sonnen.client.api.java;

import java.util.Map;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class JsonObject<T> {

    private String json;
    private Map<String, Object> data;

    public JsonObject(String json, Map<String, Object> data) {
        this.json = json;
        this.data = data;
    }

    public String getJson() {
        return json;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
