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
public interface MicrogridStatus {
    Boolean getContiniousPowerViolation();

    Boolean getDischargeCurrentLimitViolation();

    Boolean getLowTemperature();

    Boolean getMaxSystemSOC();

    Boolean getMaxUserSOC();

    Boolean getMicrogridEnabled();

    Boolean getMinSystemSOC();

    Boolean getMinUserSOC();

    Boolean getOverChargeCurrent();

    Boolean getOverDischargeCurrent();

    Boolean getPeakPowerViolation();

    Boolean getProtectIsActivated();

    Boolean getTransitionToOngridPending();
}
