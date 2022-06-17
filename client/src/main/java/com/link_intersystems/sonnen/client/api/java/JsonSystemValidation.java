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

import com.link_intersystems.sonnen.client.api.SystemValidation;

import java.util.Map;


public class JsonSystemValidation implements SystemValidation {

    private Map<String, Object> properties;

    public JsonSystemValidation(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public Boolean getCountryCodeSetStatusFlag1() {
        return (Boolean) properties.get("Country Code Set status flag 1");
    }

    @Override
    public Boolean getCountryCodeSetStatusFlag2() {
        return (Boolean) properties.get("Country Code Set status flag 2");
    }

    @Override
    public Boolean getSelfTestErrorDCWiring() {
        return (Boolean) properties.get("Self test Error DC Wiring");
    }

    @Override
    public Boolean getSelfTestPostponed() {
        return (Boolean) properties.get("Self test Postponed");
    }

    @Override
    public Boolean getSelfTestPreconditionNotMet() {
        return (Boolean) properties.get("Self test Precondition not met");
    }

    @Override
    public Boolean getSelfTestRunning() {
        return (Boolean) properties.get("Self test Running");
    }

    @Override
    public Boolean getSelfTestSuccessfulFinished() {
        return (Boolean) properties.get("Self test successful finished");
    }
}
