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

import com.link_intersystems.sonnen.client.api.DCShutdownReason;

import java.util.Map;


public class JsonDCShutdownReason implements DCShutdownReason {

    private Map<String, Object> properties;

    public JsonDCShutdownReason(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public Boolean getCriticalBMSAlarm() {
        return (Boolean) properties.get("Critical BMS Alarm");
    }

    @Override
    public Boolean getElectrolyteLeakage() {
        return (Boolean) properties.get("Electrolyte Leakage");
    }

    @Override
    public Boolean getErrorConditionInBMSInitialization() {
        return (Boolean) properties.get("Error condition in BMS initialization");
    }

    @Override
    public Boolean getHWShutdown() {
        return (Boolean) properties.get("HW_Shutdown");
    }

    @Override
    public Boolean getHardWireOverVoltage() {
        return (Boolean) properties.get("HardWire Over Voltage");
    }

    @Override
    public Boolean getHardWiredDrySignalA() {
        return (Boolean) properties.get("HardWired Dry Signal A");
    }

    @Override
    public Boolean getHardWiredUnderVoltage() {
        return (Boolean) properties.get("HardWired Under Voltage");
    }

    @Override
    public Boolean getHoldingCircuitError() {
        return (Boolean) properties.get("Holding Circuit Error");
    }

    @Override
    public Boolean getInitializationTimeout() {
        return (Boolean) properties.get("Initialization Timeout");
    }

    @Override
    public Boolean getInitializationOfACContactorFailed() {
        return (Boolean) properties.get("Initialization of AC contactor failed");
    }

    @Override
    public Boolean getInitializationOfBMSHardwareFailed() {
        return (Boolean) properties.get("Initialization of BMS hardware failed");
    }

    @Override
    public Boolean getInitializationOfDCContactorFailed() {
        return (Boolean) properties.get("Initialization of DC contactor failed");
    }

    @Override
    public Boolean getInitializationOfInverterFailed() {
        return (Boolean) properties.get("Initialization of Inverter failed");
    }

    @Override
    public Boolean getInvalidOrNoSystemTypeWasSet() {
        return (Boolean) properties.get("Invalid or no SystemType was set");
    }

    @Override
    public Boolean getInverterOverTemperature() {
        return (Boolean) properties.get("Inverter Over Temperature");
    }

    @Override
    public Boolean getInverterUnderVoltage() {
        return (Boolean) properties.get("Inverter Under Voltage");
    }

    @Override
    public Boolean getInverterUnknownError() {
        return (Boolean) properties.get("Inverter Unknown Error");
    }


    @Override
    public Boolean getInverterVersionTooLowForDcModule() {
        return (Boolean) properties.get("Inverter Version Too Low For Dc-Module");
    }

    @Override
    public Boolean getManualShutdownByUser() {
        return (Boolean) properties.get("Manual shutdown by user");
    }

    @Override
    public Boolean getMinimumRSOCOfSystemReached() {
        return (Boolean) properties.get("Minimum rSOC of System reached");
    }

    @Override
    public Boolean getModulesVoltageOutOfRange() {
        return (Boolean) properties.get("Modules voltage out of range");
    }

    @Override
    public Boolean getNoSetpointReceivedByHC() {
        return (Boolean) properties.get("No Setpoint received by HC");
    }

    @Override
    public Boolean getOddNumberOfBatteryModules() {
        return (Boolean) properties.get("Odd number of battery modules");
    }

    @Override
    public Boolean getOneSingleModuleDetectedAndModuleVoltageIsOutOfRange() {
        return (Boolean) properties.get("One single module detected and module voltage is out of range");
    }

    @Override
    public Boolean getOnlyOneSingleModuleDetected() {
        return (Boolean) properties.get("Only one single module detected");
    }

    @Override
    public Boolean getShutdownTimerStarted() {
        return (Boolean) properties.get("Shutdown Timer started");
    }

    @Override
    public Boolean getSystemValidationFailed() {
        return (Boolean) properties.get("System Validation failed");
    }

    @Override
    public Boolean getVoltageMonitorChanged() {
        return (Boolean) properties.get("Voltage Monitor Changed");
    }
}
