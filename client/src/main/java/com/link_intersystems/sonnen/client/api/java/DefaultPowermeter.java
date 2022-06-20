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

import com.link_intersystems.sonnen.client.api.Powermeter;

import java.util.Map;

public class DefaultPowermeter extends MapAdapter implements Powermeter {

    public DefaultPowermeter(Map<String, Object> data) {
        super(data);
    }

    @Override
    public Number getaL1() {
        return getValue("a_l1");
    }

    @Override
    public Number getaL2() {
        return getValue("a_l2");
    }

    @Override
    public Number getaL3() {
        return getValue("a_l3");
    }

    @Override
    public Number getChannel() {
        return getValue("channel");
    }

    @Override
    public Number getDeviceid() {
        return getValue("deviceid");
    }

    @Override
    public String getDirection() {
        return getValue("direction");
    }

    @Override
    public Number getError() {
        return getValue("error");
    }

    @Override
    public Number getKwhExported() {
        return getValue("kwh_exported");
    }

    @Override
    public Number getKwhImported() {
        return getValue("kwh_imported");
    }

    @Override
    public Number getvL1L2() {
        return getValue("v_l1_l2");
    }

    @Override
    public Number getvL1N() {
        return getValue("v_l1_n");
    }

    @Override
    public Number getvL2L3() {
        return getValue("v_l2_l3");
    }

    @Override
    public Number getvL2N() {
        return getValue("v_l2_n");
    }

    @Override
    public Number getvL3L1() {
        return getValue("v_l3_l1");
    }

    @Override
    public Number getvL3N() {
        return getValue("v_l3_n");
    }

    @Override
    public Number getVaTotal() {
        return getValue("va_total");
    }

    @Override
    public Number getVarTotal() {
        return getValue("var_total");
    }

    @Override
    public Number getwL1() {
        return getValue("w_l1");
    }

    @Override
    public Number getwL2() {
        return getValue("w_l2");
    }

    @Override
    public Number getwL3() {
        return getValue("w_l3");
    }

    @Override
    public Number getwTotal() {
        return getValue("w_total");
    }

}
