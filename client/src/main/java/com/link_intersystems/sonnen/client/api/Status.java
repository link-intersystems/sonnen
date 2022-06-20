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

import java.time.LocalDateTime;

/**
 * The {@link Status} of the sonnenBatterie.
 *
 * <p>All return values are primitive wrapper types and might be <code>null</code> in case the
 * value can not be determined because of an API change.
 * </p>
 *
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface Status extends EnergyData {

    /**
     * @return the backup-buffer in percentage that is set on the system.
     */
    Integer getBackupBuffer();

    /**
     * @return a boolean that indicates the charge status. True if charging.
     */
    Boolean getBatteryCharging();

    /**
     * @return a boolean that indicates the discharge status. True if discharging.
     */
    Boolean getBatteryDischarging();

    /**
     * @return the house consumption in watts, average over the last 60s.
     */
    Integer getConsumptionAvg();

    /**
     * @return the AC frequency in hertz.
     */
    Double getFac();

    /**
     * @return a boolean that indicates the energy flow at the installation site. True if battery feeds the consumption.
     */
    Boolean getFlowConsumptionBattery();

    /**
     * @return a boolean that indicates the energy flow at the installation site. True if grid feeds the consumption.
     */
    Boolean getFlowConsumptionGrid();

    /**
     * @return a boolean that indicates the energy flow at the installation site. True if production feeds the consumption.
     */
    Boolean getFlowConsumptionProduction();

    /**
     * @return a boolean that indicates the energy flow at the installation site. True if battery is charging from grid.
     */
    Boolean getFlowGridBattery();

    /**
     * @return a boolean that indicates the energy flow at the installation site. True if production is charging the battery.
     */
    Boolean getFlowProductionBattery();

    /**
     * @return a boolean that indicates the energy flow at the installation site. True if production feeds into the grid.
     */
    Boolean getFlowProductionGrid();

    /**
     * @return if the system is installed or not. 1 means installed.
     */
    Integer getIsSystemInstalled();

    /**
     * Operating mode that is set on the system:
     * <ul>
     * <li>1: Manual charging or discharging via API</li>
     * <li>2: Automatic Self Consumption. Default</li>
     * </ul>
     *
     * @return the operating mode that is set on the system.
     */
    String getOperatingMode();

    /**
     * @return the remaining capacity based on RSOC.
     */
    Integer getRemainingCapacityWh();

    /**
     * @return a string that indicates if the system is connected to the grid ("OnGrid") or disconnected ("OffGrid")
     */
    String getSystemStatus();

    /**
     * @return the time when this data was gathered in the local system time.
     */
    LocalDateTime getTimestamp();

    /**
     * @return the AC voltage in volts.
     */
    Integer getUac();

    /**
     * @return the battery voltage in volts.
     */
    Integer getUbat();

    /**
     * @return a boolean that indicates the discharge status. True if no discharge allowed, based on battery maintenance.
     */
    Boolean getDischargeNotAllowed();

    /**
     * @return a boolean that indicates the autostart setting of the generator.
     */
    Boolean getGeneratorAutostart();

}
