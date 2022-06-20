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

import com.link_intersystems.sonnen.client.api.Latestdata;

import java.util.Map;

public class DefaultLatestdata extends MapAdapter implements Latestdata {

    public DefaultLatestdata(Map<String, Object> data) {
        super(data);
    }

    @Override
    public Integer getConsumptionW() {
        return getValue("Consumption_W");
    }

    @Override
    public Integer getFullChargeCapacity() {
        return getValue("FullChargeCapacity");
    }

    @Override
    public Integer getGridFeedInW() {
        return getValue("GridFeedIn_W");
    }

    @Override
    public Integer getPacTotalW() {
        return getValue("Pac_total_W");
    }

    @Override
    public Integer getProductionW() {
        return getValue("Production_W");
    }

    @Override
    public Integer getRsoc() {
        return getValue("RSOC");
    }

    @Override
    public Integer getSetPointW() {
        return getValue("SetPoint_W");
    }

    @Override
    public String getTimestamp() {
        return getValue("Timestamp");
    }

    @Override
    public Integer getUsoc() {
        return getValue("USOC");
    }

    @Override
    public Integer getUTCOffet() {
        return getValue("UTC_Offet");
    }

    @Override
    public DefaultIcStatus getIcStatus() {
        Map<String, Object> jsonData = getValue("ic_status");
        if (jsonData != null) {
            return new DefaultIcStatus(jsonData);
        }
        return null;
    }
}
