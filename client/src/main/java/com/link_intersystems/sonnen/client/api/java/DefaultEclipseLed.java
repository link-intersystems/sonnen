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

import com.link_intersystems.sonnen.client.api.EclipseLed;

import java.util.Map;

public class DefaultEclipseLed implements EclipseLed {

    private Map<String, Object> properties;

    public DefaultEclipseLed(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public Boolean getBlinkingRed() {
        return (Boolean) properties.get("Blinking Red");
    }

    @Override
    public Boolean getPulsingGreen() {
        return (Boolean) properties.get("Pulsing Green");
    }

    @Override
    public Boolean getPulsingOrange() {
        return (Boolean) properties.get("Pulsing Orange");
    }

    @Override
    public Boolean getPulsingWhite() {
        return (Boolean) properties.get("Pulsing White");
    }

    @Override
    public Boolean getSolidRed() {
        return (Boolean) properties.get("Solid Red");
    }
}
