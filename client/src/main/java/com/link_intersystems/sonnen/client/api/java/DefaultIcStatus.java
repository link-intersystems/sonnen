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

import com.link_intersystems.sonnen.client.api.IcStatus;

import java.util.Map;
import java.util.function.Function;

public class DefaultIcStatus implements IcStatus {

    private Map<String, Object> properties;

    public DefaultIcStatus(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public DefaultDCShutdownReason getDCShutdownReason() {
        return getObject("DC Shutdown Reason", DefaultDCShutdownReason::new);
    }

    @SuppressWarnings("unchecked")
    private <T> T getObject(String name, Function<Map<String, Object>, T> constructor) {
        Map<String, Object> jsonData = (Map<String, Object>) properties.get(name);
        if (jsonData != null) {
            return constructor.apply(jsonData);
        }
        return null;
    }

    @Override
    public DefaultEclipseLed getEclipseLed() {
        return getObject("Eclipse Led", DefaultEclipseLed::new);
    }

    @Override
    public DefaultMISCStatusBits getMISCStatusBits() {
        return getObject("MISC Status Bits", DefaultMISCStatusBits::new);
    }

    @Override
    public DefaultMicrogridStatus getMicrogridStatus() {
        return getObject("Microgrid Status", DefaultMicrogridStatus::new);
    }

    @Override
    public DefaultSetpointPriority getSetpointPriority() {
        return getObject("Setpoint Priority", DefaultSetpointPriority::new);
    }

    @Override
    public DefaultSystemValidation getSystemValidation() {
        return getObject("System Validation", DefaultSystemValidation::new);
    }

    @Override
    public Integer getNrbatterymodules() {
        return (Integer) properties.get("nrbatterymodules");
    }

    @Override
    public Integer getSecondssincefullcharge() {
        return (Integer) properties.get("secondssincefullcharge");
    }

    @Override
    public String getStatebms() {
        return (String) properties.get("statebms");
    }


    @Override
    public String getStatecorecontrolmodule() {
        return (String) properties.get("statecorecontrolmodule");
    }

    @Override
    public String getStateinverter() {
        return (String) properties.get("stateinverter");
    }

    @Override
    public String getTimestamp() {
        return (String) properties.get("timestamp");
    }
}
