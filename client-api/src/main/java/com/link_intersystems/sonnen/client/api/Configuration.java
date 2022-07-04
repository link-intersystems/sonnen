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

package com.link_intersystems.sonnen.client.api;

import static java.util.Objects.requireNonNull;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class Configuration<T> {

    public static final Configuration<Integer> CM_MarketingModuleCapacity = new Configuration<>("CM_MarketingModuleCapacity", Integer.class);
    public static final Configuration<String> DE_Software = new Configuration<>("DE_Software", String.class);
    public static final Configuration<Integer> EM_OperatingMode = new Configuration<>("EM_OperatingMode", Integer.class);
    public static final Configuration<Integer> EM_USOC = new Configuration<>("EM_USOC", Integer.class);
    public static final Configuration<Integer> IC_BatteryModules = new Configuration<>("IC_BatteryModules", Integer.class);
    public static final Configuration<Integer> IC_InverterMaxPower_w = new Configuration<>("IC_InverterMaxPower_w", Integer.class);
    public static final Configuration<Integer> NVM_PfcFixedCosPhi = new Configuration<>("NVM_PfcFixedCosPhi", Integer.class);
    public static final Configuration<Integer> NVM_PfcIsFixedCosPhiActive = new Configuration<>("NVM_PfcIsFixedCosPhiActive", Integer.class);
    public static final Configuration<Integer> NVM_PfcIsFixedCosPhiLagging = new Configuration<>("NVM_PfcIsFixedCosPhiLagging", Integer.class);
    /**
     * some kind of array, but I have not found out what this configuration is about.
     */
    public static final Configuration<Object> EM_ToU_Schedule = new Configuration<>("EM_ToU_Schedule", Object.class);

    // Please make a pull request, if you know anything about these configurations.
    public static final Configuration<Object> EM_US_CHP_Max_SOC = new Configuration<>("EM_US_CHP_Max_SOC", Object.class);
    public static final Configuration<Object> EM_US_CHP_Min_SOC = new Configuration<>("EM_US_CHP_Min_SOC", Object.class);
    public static final Configuration<Object> EM_US_GENRATOR_TYPE = new Configuration<>("EM_US_GENRATOR_TYPE", Object.class);
    public static final Configuration<Object> EM_US_GEN_POWER_SET_POINT = new Configuration<>("EM_US_GEN_POWER_SET_POINT", Object.class);
    public static final Configuration<Object> EM_US_RE_ENABLE_MICROGRID = new Configuration<>("EM_US_RE_ENABLE_MICROGRID", Object.class);
    public static final Configuration<Object> EM_US_USER_INPUT_TIME_ONE = new Configuration<>("EM_US_USER_INPUT_TIME_ONE", Object.class);
    public static final Configuration<Object> EM_US_USER_INPUT_TIME_THREE = new Configuration<>("EM_US_USER_INPUT_TIME_THREE", Object.class);
    public static final Configuration<Object> EM_US_USER_INPUT_TIME_TWO = new Configuration<>("EM_US_USER_INPUT_TIME_TWO", Object.class);

    private String name;
    private Class<T> type;

    public Configuration(String name, Class<T> type) {
        this.name = requireNonNull(name);
        this.type = requireNonNull(type);
    }

    public String getName() {
        return name;
    }

    public Class<T> getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    public T cast(Object value) {
        if (getType().isInstance(value)) {
            return (T) value;
        }

        Class<T> type = getType();
        if (Integer.class.equals(type)) {
            return type.cast(Integer.parseInt(String.valueOf(value)));
        }

        return (T) value;
    }
}
