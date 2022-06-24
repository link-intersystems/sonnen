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

package com.link_intersystems.sonnen.client.api.impl;

import com.link_intersystems.sonnen.client.api.Latestdata;

import java.util.Map;

public class DefaultLatestdata implements Latestdata {

    private Map<String, Object> data;

    public DefaultLatestdata(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public Integer getConsumptionW() {
        return (Integer) data.get("Consumption_W");
    }

    @Override
    public Integer getFullChargeCapacity() {
        return (Integer) data.get("FullChargeCapacity");
    }

    @Override
    public Integer getGridFeedInW() {
        return (Integer) data.get("GridFeedIn_W");
    }

    @Override
    public Integer getPacTotalW() {
        return (Integer) data.get("Pac_total_W");
    }

    @Override
    public Integer getProductionW() {
        return (Integer) data.get("Production_W");
    }

    @Override
    public Integer getRsoc() {
        return (Integer) data.get("RSOC");
    }

    @Override
    public Integer getSetPointW() {
        return (Integer) data.get("SetPoint_W");
    }

    @Override
    public String getTimestamp() {
        return (String) data.get("Timestamp");
    }

    @Override
    public Integer getUsoc() {
        return (Integer) data.get("USOC");
    }

    @Override
    public Integer getUTCOffet() {
        return (Integer) data.get("UTC_Offet");
    }

    @SuppressWarnings("unchecked")
    @Override
    public DefaultIcStatus getIcStatus() {
        Map<String, Object> jsonData = (Map<String, Object>) data.get("ic_status");
        if (jsonData != null) {
            return new DefaultIcStatus(jsonData);
        }
        return null;
    }
}
