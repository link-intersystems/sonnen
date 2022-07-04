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

import com.link_intersystems.sonnen.client.api.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DefaultStatus implements Status {

    private Map<String, Object> data;

    public DefaultStatus(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public Integer getBackupBuffer() {
        String backupBuffer = (String) data.get("BackupBuffer");
        if (backupBuffer == null) {
            return null;
        }
        return Integer.parseInt(backupBuffer);
    }

    @Override
    public Boolean getBatteryCharging() {
        return (Boolean) data.get("BatteryCharging");
    }

    @Override
    public Boolean getBatteryDischarging() {
        return (Boolean) data.get("BatteryDischarging");
    }

    @Override
    public Integer getConsumptionAvg() {
        return (Integer) data.get("Consumption_Avg");
    }

    @Override
    public Integer getConsumptionW() {
        return (Integer) data.get("Consumption_W");
    }

    @Override
    public Double getFac() {
        return (Double) data.get("Fac");
    }

    @Override
    public Boolean getFlowConsumptionBattery() {
        return (Boolean) data.get("FlowConsumptionBattery");
    }

    @Override
    public Boolean getFlowConsumptionGrid() {
        return (Boolean) data.get("FlowConsumptionGrid");
    }

    @Override
    public Boolean getFlowConsumptionProduction() {
        return (Boolean) data.get("FlowConsumptionProduction");
    }

    @Override
    public Boolean getFlowGridBattery() {
        return (Boolean) data.get("FlowGridBattery");
    }

    @Override
    public Boolean getFlowProductionBattery() {
        return (Boolean) data.get("FlowProductionBattery");
    }

    @Override
    public Boolean getFlowProductionGrid() {
        return (Boolean) data.get("FlowProductionGrid");
    }

    @Override
    public Integer getGridFeedInW() {
        return (Integer) data.get("GridFeedIn_W");
    }

    @Override
    public Integer getIsSystemInstalled() {
        return (Integer) data.get("IsSystemInstalled");
    }

    @Override
    public String getOperatingMode() {
        return (String) data.get("OperatingMode");
    }

    @Override
    public Integer getPacTotalW() {
        return (Integer) data.get("Pac_total_W");
    }

    @Override
    public Integer getProductionW() {
        return (Integer) data.get("Production_W");
    }

    @Override
    public Integer getRsoc() {
        return (Integer) data.get("RSOC");
    }

    @Override
    public Integer getRemainingCapacityWh() {
        return (Integer) data.get("RemainingCapacity_Wh");
    }

    @Override
    public String getSystemStatus() {
        return (String) data.get("SystemStatus");
    }

    @Override
    public LocalDateTime getTimestamp() {
        String timestamp = (String) data.get("Timestamp");
        if (timestamp != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(timestamp, dateTimeFormatter);
        }

        return null;
    }

    @Override
    public Integer getUsoc() {
        return (Integer) data.get("USOC");
    }

    @Override
    public Integer getUac() {
        return (Integer) data.get("Uac");
    }

    @Override
    public Integer getUbat() {
        return (Integer) data.get("Ubat");
    }

    @Override
    public Boolean getDischargeNotAllowed() {
        return (Boolean) data.get("dischargeNotAllowed");
    }

    @Override
    public Boolean getGeneratorAutostart() {
        return (Boolean) data.get("generator_autostart");
    }
}
