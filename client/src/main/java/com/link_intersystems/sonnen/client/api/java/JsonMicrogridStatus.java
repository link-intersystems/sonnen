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

import com.link_intersystems.sonnen.client.api.MicrogridStatus;

import java.util.Map;


public class JsonMicrogridStatus implements MicrogridStatus {

    private Map<String, Object> properties;

    public JsonMicrogridStatus(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public Boolean getContiniousPowerViolation() {
        return (Boolean) properties.get("Continious Power Violation");
    }

    @Override
    public Boolean getDischargeCurrentLimitViolation() {
        return (Boolean) properties.get("Discharge Current Limit Violation");
    }

    @Override
    public Boolean getLowTemperature() {
        return (Boolean) properties.get("Low Temperature");
    }

    @Override
    public Boolean getMaxSystemSOC() {
        return (Boolean) properties.get("Max System SOC");
    }

    @Override
    public Boolean getMaxUserSOC() {
        return (Boolean) properties.get("Max User SOC");
    }

    @Override
    public Boolean getMicrogridEnabled() {
        return (Boolean) properties.get("Microgrid Enabled");
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
    public Boolean getOverChargeCurrent() {
        return (Boolean) properties.get("Over Charge Current");
    }

    @Override
    public Boolean getOverDischargeCurrent() {
        return (Boolean) properties.get("Over Discharge Current");
    }

    @Override
    public Boolean getPeakPowerViolation() {
        return (Boolean) properties.get("Peak Power Violation");
    }

    @Override
    public Boolean getProtectIsActivated() {
        return (Boolean) properties.get("Protect is activated");
    }

    @Override
    public Boolean getTransitionToOngridPending() {
        return (Boolean) properties.get("Transition to Ongrid Pending");
    }
}
