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

import com.link_intersystems.sonnen.client.api.java.*;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface IcStatus {
    DefaultDCShutdownReason getDCShutdownReason();

    DefaultEclipseLed getEclipseLed();

    DefaultMISCStatusBits getMISCStatusBits();

    DefaultMicrogridStatus getMicrogridStatus();

    DefaultSetpointPriority getSetpointPriority();

    DefaultSystemValidation getSystemValidation();

    Integer getNrbatterymodules();

    Integer getSecondssincefullcharge();

    String getStatebms();

    String getStatecorecontrolmodule();

    String getStateinverter();

    String getTimestamp();
}
