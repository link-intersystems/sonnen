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
