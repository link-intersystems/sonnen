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

import com.link_intersystems.net.http.HttpClient;
import com.link_intersystems.net.http.test.HttpMockServer;
import com.link_intersystems.net.http.test.OngoingRequestMocking;
import com.link_intersystems.net.http.test.junit.HttpMockServerExtension;
import com.link_intersystems.sonnen.client.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
@ExtendWith(HttpMockServerExtension.class)
class DefaultSonnenClientTest {

    private HttpMockServer httpMockServer;
    private DefaultSonnenClient sonnenClient;

    @BeforeEach
    void setUp(HttpMockServer httpMockServer) {
        this.httpMockServer = httpMockServer;

        DefaultSonnenClientProperties properties = new DefaultSonnenClientProperties();
        properties.setApiUrl("http://localhost:" + httpMockServer.getPort() + "/");
        properties.setApiToken("TOKEN_123");
        SonnenRestClient restClient = new SonnenRestClient(properties, new HttpClient(), new JacksonJsonFormat());
        sonnenClient = new DefaultSonnenClient(restClient);
    }

    @Test
    void getLatestdata() throws IOException, SonnenClientException {
        OngoingRequestMocking ongoingRequestMocking = httpMockServer.whenRequestPath("/latestdata");
        ongoingRequestMocking.respond(200, getBytes("/latestdata.json"));

        Latestdata latestdata = sonnenClient.getLatestdata();
        assertNotNull(latestdata);

        assertEquals(9765, latestdata.getFullChargeCapacity());
        assertEquals(467, latestdata.getConsumptionW());
        assertEquals("2022-06-23 06:43:17", latestdata.getTimestamp());
        assertEquals(-29, latestdata.getGridFeedInW());
        assertEquals(62, latestdata.getProductionW());
        assertEquals(370, latestdata.getSetPointW());
        assertEquals(2, latestdata.getUTCOffet());
        assertEquals(346, latestdata.getPacTotalW());
        assertEquals(75, latestdata.getRsoc());
        assertEquals(73, latestdata.getUsoc());

        DefaultIcStatus icStatus = latestdata.getIcStatus();
        assertNotNull(icStatus);
        assertEquals("Thu Jun 23 06:43:16 2022", icStatus.getTimestamp());
        assertEquals(2, icStatus.getNrbatterymodules());
        assertEquals(40639, icStatus.getSecondssincefullcharge());
        assertEquals("ready", icStatus.getStatebms());
        assertEquals("ongrid", icStatus.getStatecorecontrolmodule());
        assertEquals("running", icStatus.getStateinverter());

        DefaultMicrogridStatus microgridStatus = icStatus.getMicrogridStatus();
        assertNotNull(microgridStatus);
        assertEquals(false, microgridStatus.getMicrogridEnabled());
        assertEquals(false, microgridStatus.getContiniousPowerViolation());
        assertEquals(false, microgridStatus.getDischargeCurrentLimitViolation());
        assertEquals(false, microgridStatus.getLowTemperature());
        assertEquals(false, microgridStatus.getMaxSystemSOC());
        assertEquals(false, microgridStatus.getMinSystemSOC());
        assertEquals(false, microgridStatus.getOverChargeCurrent());
        assertEquals(false, microgridStatus.getOverDischargeCurrent());
        assertEquals(false, microgridStatus.getPeakPowerViolation());
        assertEquals(true, microgridStatus.getProtectIsActivated());
        assertEquals(false, microgridStatus.getTransitionToOngridPending());

        DefaultDCShutdownReason dcShutdownReason = icStatus.getDCShutdownReason();
        assertNotNull(dcShutdownReason);
        assertEquals(false, dcShutdownReason.getHWShutdown());
        assertEquals(false, dcShutdownReason.getCriticalBMSAlarm());
        assertEquals(false, dcShutdownReason.getElectrolyteLeakage());
        assertEquals(false, dcShutdownReason.getErrorConditionInBMSInitialization());
        assertEquals(false, dcShutdownReason.getCriticalBMSAlarm());
        assertEquals(false, dcShutdownReason.getHardWiredDrySignalA());
        assertEquals(false, dcShutdownReason.getHardWireOverVoltage());
        assertEquals(false, dcShutdownReason.getHoldingCircuitError());
        assertEquals(false, dcShutdownReason.getInitializationOfACContactorFailed());
        assertEquals(false, dcShutdownReason.getInitializationOfBMSHardwareFailed());
        assertEquals(false, dcShutdownReason.getInitializationOfInverterFailed());
        assertEquals(false, dcShutdownReason.getInitializationTimeout());
        assertEquals(false, dcShutdownReason.getInvalidOrNoSystemTypeWasSet());
        assertEquals(false, dcShutdownReason.getInverterOverTemperature());
        assertEquals(false, dcShutdownReason.getInverterUnderVoltage());
        assertEquals(false, dcShutdownReason.getInverterUnknownError());
        assertEquals(false, dcShutdownReason.getInverterVersionTooLowForDcModule());
        assertEquals(false, dcShutdownReason.getManualShutdownByUser());
        assertEquals(false, dcShutdownReason.getMinimumRSOCOfSystemReached());
        assertEquals(false, dcShutdownReason.getModulesVoltageOutOfRange());
        assertEquals(false, dcShutdownReason.getHardWiredUnderVoltage());
        assertEquals(false, dcShutdownReason.getInitializationOfDCContactorFailed());
        assertEquals(false, dcShutdownReason.getNoSetpointReceivedByHC());
        assertEquals(false, dcShutdownReason.getOddNumberOfBatteryModules());
        assertEquals(false, dcShutdownReason.getOneSingleModuleDetectedAndModuleVoltageIsOutOfRange());
        assertEquals(false, dcShutdownReason.getOnlyOneSingleModuleDetected());
        assertEquals(false, dcShutdownReason.getShutdownTimerStarted());
        assertEquals(false, dcShutdownReason.getSystemValidationFailed());
        assertEquals(false, dcShutdownReason.getVoltageMonitorChanged());

        DefaultEclipseLed eclipseLed = icStatus.getEclipseLed();
        assertEquals(false, eclipseLed.getBlinkingRed());
        assertEquals(false, eclipseLed.getPulsingGreen());
        assertEquals(false, eclipseLed.getPulsingOrange());
        assertEquals(true, eclipseLed.getPulsingWhite());
        assertEquals(false, eclipseLed.getSolidRed());

        DefaultMISCStatusBits miscStatusBits = icStatus.getMISCStatusBits();
        assertEquals(false, miscStatusBits.getDischargeNotAllowed());
        assertEquals(false, miscStatusBits.getMinSystemSOC());
        assertEquals(false, miscStatusBits.getMinUserSOC());
        assertEquals(false, miscStatusBits.getF1Open());
        assertEquals(false, miscStatusBits.getSetpointTimeout());

        DefaultSetpointPriority setpointPriority = icStatus.getSetpointPriority();
        assertEquals(false, setpointPriority.getBms());
        assertEquals(false, setpointPriority.getMinUserSOC());
        assertEquals(true, setpointPriority.getEnergyManager());
        assertEquals(false, setpointPriority.getInverter());
        assertEquals(false, setpointPriority.getFullChargeRequest());
        assertEquals(false, setpointPriority.getTrickleCharge());

        DefaultSystemValidation systemValidation = icStatus.getSystemValidation();
        assertEquals(false, systemValidation.getCountryCodeSetStatusFlag1());
        assertEquals(false, systemValidation.getCountryCodeSetStatusFlag2());
        assertEquals(false, systemValidation.getSelfTestErrorDCWiring());
        assertEquals(false, systemValidation.getSelfTestPostponed());
        assertEquals(false, systemValidation.getSelfTestPreconditionNotMet());
        assertEquals(false, systemValidation.getSelfTestSuccessfulFinished());
        assertEquals(false, systemValidation.getSelfTestRunning());
    }

    @Test
    void getStatus() throws SonnenClientException, IOException {
        OngoingRequestMocking ongoingRequestMocking = httpMockServer.whenRequestPath("/status");
        ongoingRequestMocking.respond(200, getBytes("/status.json"));

        Status status = sonnenClient.getStatus();
        assertNotNull(status);

        assertEquals(15, status.getBackupBuffer());
        assertEquals(false, status.getBatteryCharging());
        assertEquals(true, status.getBatteryDischarging());
        assertEquals(457, status.getConsumptionAvg());
        assertEquals(436, status.getConsumptionW());
        assertEquals(49.9890022277832, status.getFac());
        assertEquals(true, status.getFlowConsumptionBattery());
        assertEquals(true, status.getFlowConsumptionGrid());
        assertEquals(true, status.getFlowConsumptionProduction());
        assertEquals(false, status.getFlowGridBattery());
        assertEquals(false, status.getFlowProductionBattery());
        assertEquals(false, status.getFlowProductionGrid());
        assertEquals(-28, status.getGridFeedInW());
        assertEquals(1, status.getIsSystemInstalled());
        assertEquals("2", status.getOperatingMode());
        assertEquals(329, status.getPacTotalW());
        assertEquals(64, status.getProductionW());
        assertEquals(75, status.getRsoc());
        assertEquals(15503, status.getRemainingCapacityWh());
        assertEquals("OnGrid", status.getSystemStatus());
        assertEquals(LocalDateTime.parse("2022-06-23T06:44:27"), status.getTimestamp());
        assertEquals(73, status.getUsoc());
        assertEquals(237, status.getUac());
        assertEquals(212, status.getUbat());
        assertEquals(false, status.getDischargeNotAllowed());
        assertEquals(false, status.getGeneratorAutostart());
    }

    private byte[] getBytes(String resource) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try (InputStream in = new BufferedInputStream(DefaultSonnenClientTest.class.getResourceAsStream(resource))) {
            int read;
            while ((read = in.read()) != -1) {
                bout.write(read);
            }
        }

        return bout.toByteArray();
    }

    @Test
    void getConfiguration() throws IOException, SonnenClientException {
        OngoingRequestMocking ongoingRequestMocking = httpMockServer.whenRequestPath("/configurations/IC_BatteryModules");
        ongoingRequestMocking.respond(200, getBytes("/configurations_ic_battery_modules.json"));

        Integer batteryModules = sonnenClient.getConfiguration(Configuration.IC_BatteryModules);

        assertEquals(2, batteryModules);
    }

    @Test
    void setConfiguration() {
    }

    @Test
    void getPowermeter() throws SonnenClientException, IOException {
        OngoingRequestMocking ongoingRequestMocking = httpMockServer.whenRequestPath("/powermeter");
        ongoingRequestMocking.respond(200, getBytes("/powermeter.json"));

        List<Powermeter> powermeter = sonnenClient.getPowermeter();
        assertNotNull(powermeter);
        assertEquals(2, powermeter.size());

        Powermeter powermeter1 = powermeter.get(0);
        assertEquals("production", powermeter1.getDirection());
        assertEquals(0.8190000057220459, powermeter1.getaL1().doubleValue());
        assertEquals(0.9350000023841858, powermeter1.getaL2().doubleValue());
        assertEquals(0.0, powermeter1.getaL3().doubleValue());

        assertEquals(27.899999618530273, powermeter1.getwL1().doubleValue());
        assertEquals(31.700000762939453, powermeter1.getwL2().doubleValue());
        assertEquals(0.0, powermeter1.getwL3().doubleValue());

        assertEquals(0.8190000057220459, powermeter1.getaL1().doubleValue());
        assertEquals(0.9350000023841858, powermeter1.getaL2().doubleValue());
        assertEquals(0.0, powermeter1.getaL3().doubleValue());

        assertEquals(413.29998779296875, powermeter1.getvL1L2().doubleValue());
        assertEquals(416.0, powermeter1.getvL2L3().doubleValue());
        assertEquals(412.79998779296875, powermeter1.getvL3L1().doubleValue());

        assertEquals(1.0, powermeter1.getChannel().doubleValue());
        assertEquals(4.0, powermeter1.getDeviceid().doubleValue());
        assertEquals(0.0, powermeter1.getError().doubleValue());
        assertEquals(0.0, powermeter1.getKwhExported().doubleValue());
        assertEquals(2250.5, powermeter1.getKwhImported().doubleValue());
        assertEquals(410.8999938964844, powermeter1.getVarTotal().doubleValue());
        assertEquals(415.1000061035156, powermeter1.getVaTotal().doubleValue());

        assertEquals(235.6999969482422, powermeter1.getvL1N().doubleValue());
        assertEquals(237.8000030517578, powermeter1.getvL2N().doubleValue());
        assertEquals(236.39999389648438, powermeter1.getvL3N().doubleValue());

        assertEquals(59.599998474121094, powermeter1.getwTotal().doubleValue());

        Powermeter powermeter2 = powermeter.get(1);
        assertEquals("consumption", powermeter2.getDirection());
        assertEquals(9.751999855041504, powermeter2.getaL1().doubleValue());
        assertEquals(0.5379999876022339, powermeter2.getaL2().doubleValue());
        assertEquals(1.4839999675750732, powermeter2.getaL3().doubleValue());

        assertEquals(2296.39990234375, powermeter2.getwL1().doubleValue());
        assertEquals(105.69999694824219, powermeter2.getwL2().doubleValue());
        assertEquals(236.6999969482422, powermeter2.getwL3().doubleValue());

        assertEquals(9.751999855041504, powermeter2.getaL1().doubleValue());
        assertEquals(0.5379999876022339, powermeter2.getaL2().doubleValue());
        assertEquals(1.4839999675750732, powermeter2.getaL3().doubleValue());

        assertEquals(413.29998779296875, powermeter2.getvL1L2().doubleValue());
        assertEquals(416.0, powermeter2.getvL2L3().doubleValue());
        assertEquals(412.79998779296875, powermeter2.getvL3L1().doubleValue());

        assertEquals(2.0, powermeter2.getChannel().doubleValue());
        assertEquals(4.0, powermeter2.getDeviceid().doubleValue());
        assertEquals(0.0, powermeter2.getError().doubleValue());
        assertEquals(0.0, powermeter2.getKwhExported().doubleValue());
        assertEquals(976.9000244140625, powermeter2.getKwhImported().doubleValue());
        assertEquals(-232.8000030517578, powermeter2.getVarTotal().doubleValue());
        assertEquals(2649.0, powermeter2.getVaTotal().doubleValue());

        assertEquals(235.6999969482422, powermeter2.getvL1N().doubleValue());
        assertEquals(237.6999969482422, powermeter2.getvL2N().doubleValue());
        assertEquals(236.39999389648438, powermeter2.getvL3N().doubleValue());

        assertEquals(2638.800048828125, powermeter2.getwTotal().doubleValue());
    }

    @Test
    void discharge() {
    }

    @Test
    void charge() {
    }

    @Test
    void postSetpoint() {
    }
}