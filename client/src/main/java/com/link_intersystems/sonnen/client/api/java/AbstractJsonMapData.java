package com.link_intersystems.sonnen.client.api.java;

import java.util.Map;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class AbstractJsonMapData {

    protected Map<String, Object> properties;

    public AbstractJsonMapData(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return new JsonFormatter(this).toString();
    }

}
