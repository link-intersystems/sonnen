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

/**
 * Common energy data.
 *
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface EnergyData {
    /**
     * @return the house consumption in watts, direct measurement.
     */
    Integer getConsumptionW();

    /**
     * @return the grid Feed in. Negative is consumption and positive is feed in.
     */
    Integer getGridFeedInW();

    /**
     * @return the AC Power greater than ZERO is discharging Inverter AC Power less than ZERO is charging.
     */
    Integer getPacTotalW();

    /**
     * @return the PV production in watts.
     */
    Integer getProductionW();

    /**
     * @return the relative state of charge.
     */
    Integer getRsoc();

    /**
     * @return the user state of charge in percent. This is usually the battery capacity that a user will see
     * in the my.sonnen.de web frontend.
     */
    Integer getUsoc();
}
