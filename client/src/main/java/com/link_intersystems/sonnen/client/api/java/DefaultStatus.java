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

import com.link_intersystems.sonnen.client.api.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DefaultStatus extends MapAdapter implements Status {

    public DefaultStatus(Map<String, Object> data) {
        super(data);
    }

    @Override
    public Integer getBackupBuffer() {
        String backupBuffer = getValue("BackupBuffer");
        if (backupBuffer == null) {
            return null;
        }
        return Integer.parseInt(backupBuffer);
    }

    @Override
    public Boolean getBatteryCharging() {
        return getValue("BatteryCharging");
    }

    @Override
    public Boolean getBatteryDischarging() {
        return getValue("BatteryDischarging");
    }

    @Override
    public Integer getConsumptionAvg() {
        return getValue("Consumption_Avg");
    }

    @Override
    public Integer getConsumptionW() {
        return getValue("Consumption_W");
    }

    @Override
    public Double getFac() {
        return getValue("Fac");
    }

    @Override
    public Boolean getFlowConsumptionBattery() {
        return getValue("FlowConsumptionBattery");
    }

    @Override
    public Boolean getFlowConsumptionGrid() {
        return getValue("FlowConsumptionGrid");
    }

    @Override
    public Boolean getFlowConsumptionProduction() {
        return getValue("FlowConsumptionProduction");
    }

    @Override
    public Boolean getFlowGridBattery() {
        return getValue("FlowGridBattery");
    }

    @Override
    public Boolean getFlowProductionBattery() {
        return getValue("FlowProductionBattery");
    }

    @Override
    public Boolean getFlowProductionGrid() {
        return getValue("FlowProductionGrid");
    }

    @Override
    public Integer getGridFeedInW() {
        return getValue("GridFeedIn_W");
    }

    @Override
    public Integer getIsSystemInstalled() {
        return getValue("IsSystemInstalled");
    }

    @Override
    public String getOperatingMode() {
        return getValue("OperatingMode");
    }

    @Override
    public Integer getPacTotalW() {
        return getValue("Pac_total_W");
    }

    @Override
    public Integer getProductionW() {
        return getValue("Production_W");
    }

    @Override
    public Integer getRsoc() {
        return getValue("RSOC");
    }

    @Override
    public Integer getRemainingCapacityWh() {
        return getValue("RemainingCapacity_Wh");
    }

    @Override
    public String getSystemStatus() {
        return getValue("SystemStatus");
    }

    @Override
    public LocalDateTime getTimestamp() {
        String timestamp = getValue("Timestamp");
        if (timestamp != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(timestamp, dateTimeFormatter);
        }

        return null;
    }

    @Override
    public Integer getUsoc() {
        return getValue("USOC");
    }

    @Override
    public Integer getUac() {
        return getValue("Uac");
    }

    @Override
    public Integer getUbat() {
        return getValue("Ubat");
    }

    @Override
    public Boolean getDischargeNotAllowed() {
        return getValue("dischargeNotAllowed");
    }

    @Override
    public Boolean getGeneratorAutostart() {
        return getValue("generator_autostart");
    }
}
