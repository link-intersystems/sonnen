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
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface DCShutdownReason {
    Boolean getCriticalBMSAlarm();

    Boolean getElectrolyteLeakage();

    Boolean getErrorConditionInBMSInitialization();

    Boolean getHWShutdown();

    Boolean getHardWireOverVoltage();

    Boolean getHardWiredDrySignalA();

    Boolean getHardWiredUnderVoltage();

    Boolean getHoldingCircuitError();

    Boolean getInitializationTimeout();

    Boolean getInitializationOfACContactorFailed();

    Boolean getInitializationOfBMSHardwareFailed();

    Boolean getInitializationOfDCContactorFailed();

    Boolean getInitializationOfInverterFailed();

    Boolean getInvalidOrNoSystemTypeWasSet();

    Boolean getInverterOverTemperature();

    Boolean getInverterUnderVoltage();

    Boolean getInverterUnknownError();

    Boolean getInverterVersionTooLowForDcModule();

    Boolean getManualShutdownByUser();

    Boolean getMinimumRSOCOfSystemReached();

    Boolean getModulesVoltageOutOfRange();

    Boolean getNoSetpointReceivedByHC();

    Boolean getOddNumberOfBatteryModules();

    Boolean getOneSingleModuleDetectedAndModuleVoltageIsOutOfRange();

    Boolean getOnlyOneSingleModuleDetected();

    Boolean getShutdownTimerStarted();

    Boolean getSystemValidationFailed();

    Boolean getVoltageMonitorChanged();
}
