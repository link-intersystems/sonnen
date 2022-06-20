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

package com.link_intersystems.sonnen.datalogger.gateway;

import com.link_intersystems.sonnen.client.api.Status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
class EntityPersistenceConverter {

    private ZoneId zoneId;

    EntityPersistenceConverter(ZoneId zoneId) {
        this.zoneId = requireNonNull(zoneId);
    }

    public Map<String, Object> convert(Status status) {

        LinkedHashMap<String, Object> beanMap = new LinkedHashMap<>();

        LocalDateTime timestamp = status.getTimestamp();
        long systemTimeAtZone = timestamp.atZone(zoneId).toInstant().toEpochMilli();
        String idName = "_id";
        beanMap.put(idName, systemTimeAtZone);

        beanMap.put("timestamp", status.getTimestamp());

        beanMap.put("productionW", status.getProductionW());
        beanMap.put("consumptionW", status.getConsumptionW());
        beanMap.put("gridFeedInW", status.getGridFeedInW());
        beanMap.put("pacTotalW", status.getPacTotalW());
        beanMap.put("consumptionAvg", status.getConsumptionAvg());
        beanMap.put("remainingCapacityWh", status.getRemainingCapacityWh());

        beanMap.put("systemStatus", status.getSystemStatus());
        beanMap.put("operatingMode", status.getOperatingMode());
        beanMap.put("backupBuffer", status.getBackupBuffer());
        beanMap.put("usoc", status.getUsoc());
        beanMap.put("rsoc", status.getRsoc());
        beanMap.put("uac", status.getUac());
        beanMap.put("fac", status.getFac());
        beanMap.put("ubat", status.getUbat());
        beanMap.put("batteryCharging", status.getBatteryCharging());
        beanMap.put("batteryDischarging", status.getBatteryDischarging());


        beanMap.put("flowConsumptionBattery", status.getFlowConsumptionBattery());
        beanMap.put("flowConsumptionGrid", status.getFlowConsumptionGrid());
        beanMap.put("flowConsumptionProduction", status.getFlowConsumptionProduction());
        beanMap.put("flowGridBattery", status.getFlowGridBattery());
        beanMap.put("flowProductionBattery", status.getFlowProductionBattery());
        beanMap.put("flowProductionGrid", status.getFlowProductionGrid());

        beanMap.put("dischargeNotAllowed", status.getDischargeNotAllowed());
        beanMap.put("generatorAutostart", status.getGeneratorAutostart());
        beanMap.put("isSystemInstalled", status.getIsSystemInstalled());

        return beanMap;
    }
}
