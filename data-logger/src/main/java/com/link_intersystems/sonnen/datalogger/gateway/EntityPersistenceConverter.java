package com.link_intersystems.sonnen.datalogger.gateway;

import com.link_intersystems.beans.java.InterfaceBeanInfo;
import com.link_intersystems.beans.java.MergedAdditionalBeanInfo;
import com.link_intersystems.sonnen.client.api.JsonData;
import com.link_intersystems.sonnen.client.api.Status;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
class EntityPersistenceConverter {

    private String idName = "_id";

    private final MergedAdditionalBeanInfo statusBeanInfo;
    private ZoneId zoneId;

    EntityPersistenceConverter(ZoneId zoneId) {
        this.zoneId = requireNonNull(zoneId);
        try {
            BeanInfo statusBeanInfo = new InterfaceBeanInfo(Status.class, JsonData.class);
            this.statusBeanInfo = new MergedAdditionalBeanInfo(statusBeanInfo);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> convert(Status status) {

        LinkedHashMap<String, Object> beanMap = new LinkedHashMap<>();

        LocalDateTime timestamp = status.getTimestamp();
        long systemTimeAtZone = timestamp.atZone(zoneId).toInstant().toEpochMilli();
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
