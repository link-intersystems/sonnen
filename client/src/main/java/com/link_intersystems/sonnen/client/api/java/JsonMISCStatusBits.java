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

import com.link_intersystems.sonnen.client.api.MISCStatusBits;

import java.util.Map;

public class JsonMISCStatusBits implements MISCStatusBits {


    private Map<String, Object> properties;

    public JsonMISCStatusBits(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public Boolean getDischargeNotAllowed() {
        return (Boolean) properties.get("Discharge not allowed");
    }


    @Override
    public Boolean getF1Open() {
        return (Boolean) properties.get("F1 open");
    }

    @Override
    public Boolean getMinSystemSOC() {
        return (Boolean) properties.get("Min System SOC");
    }

    @Override
    public Boolean getMinUserSOC() {
        return (Boolean) properties.get("Min User SOC");
    }

    @Override
    public Boolean getSetpointTimeout() {
        return (Boolean) properties.get("Setpoint Timeout");
    }
}
