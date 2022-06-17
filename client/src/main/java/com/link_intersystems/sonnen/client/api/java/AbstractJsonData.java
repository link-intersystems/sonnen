/*
 * Copyright (C) 2022 Link Intersystems GmbH
 *
 * This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *   SPDX-License-Identifier: EPL-2.0
 *
 *   Contributors:
 *       Link Intersystems GmbH - initial API and implementation
 */

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
