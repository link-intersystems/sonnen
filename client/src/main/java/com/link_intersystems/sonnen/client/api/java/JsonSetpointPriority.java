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

import com.link_intersystems.sonnen.client.api.SetpointPriority;

import java.util.Map;

public class JsonSetpointPriority implements SetpointPriority {

    private Map<String, Object> properties;

    public JsonSetpointPriority(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public Boolean getBms() {
        return (Boolean) properties.get("BMS");
    }

    @Override
    public Boolean getEnergyManager() {
        return (Boolean) properties.get("Energy Manager");
    }

    @Override
    public Boolean getFullChargeRequest() {
        return (Boolean) properties.get("Full Charge Request");
    }

    @Override
    public Boolean getInverter() {
        return (Boolean) properties.get("Inverter");
    }

    @Override
    public Boolean getMinUserSOC() {
        return (Boolean) properties.get("Min User SOC");
    }

    @Override
    public Boolean getTrickleCharge() {
        return (Boolean) properties.get("Trickle Charge");
    }

}
