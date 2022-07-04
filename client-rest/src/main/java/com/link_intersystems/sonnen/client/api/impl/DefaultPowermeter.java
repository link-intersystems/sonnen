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

import com.link_intersystems.sonnen.client.api.Powermeter;

import java.util.Map;

public class DefaultPowermeter implements Powermeter {

    private Map<String, Object> data;

    public DefaultPowermeter(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public Number getaL1() {
        return (Number) data.get("a_l1");
    }

    @Override
    public Number getaL2() {
        return (Number) data.get("a_l2");
    }

    @Override
    public Number getaL3() {
        return (Number) data.get("a_l3");
    }

    @Override
    public Number getChannel() {
        return (Number) data.get("channel");
    }

    @Override
    public Number getDeviceid() {
        return (Number) data.get("deviceid");
    }

    @Override
    public String getDirection() {
        return (String) data.get("direction");
    }

    @Override
    public Number getError() {
        return (Number) data.get("error");
    }

    @Override
    public Number getKwhExported() {
        return (Number) data.get("kwh_exported");
    }

    @Override
    public Number getKwhImported() {
        return (Number) data.get("kwh_imported");
    }

    @Override
    public Number getvL1L2() {
        return (Number) data.get("v_l1_l2");
    }

    @Override
    public Number getvL1N() {
        return (Number) data.get("v_l1_n");
    }

    @Override
    public Number getvL2L3() {
        return (Number) data.get("v_l2_l3");
    }

    @Override
    public Number getvL2N() {
        return (Number) data.get("v_l2_n");
    }

    @Override
    public Number getvL3L1() {
        return (Number) data.get("v_l3_l1");
    }

    @Override
    public Number getvL3N() {
        return (Number) data.get("v_l3_n");
    }

    @Override
    public Number getVaTotal() {
        return (Number) data.get("va_total");
    }

    @Override
    public Number getVarTotal() {
        return (Number) data.get("var_total");
    }

    @Override
    public Number getwL1() {
        return (Number) data.get("w_l1");
    }

    @Override
    public Number getwL2() {
        return (Number) data.get("w_l2");
    }

    @Override
    public Number getwL3() {
        return (Number) data.get("w_l3");
    }

    @Override
    public Number getwTotal() {
        return (Number) data.get("w_total");
    }

}
