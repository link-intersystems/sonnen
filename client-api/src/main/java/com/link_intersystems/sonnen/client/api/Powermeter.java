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
public interface Powermeter {
    Number getaL1();

    Number getaL2();

    Number getaL3();

    Number getChannel();

    Number getDeviceid();

    String getDirection();

    Number getError();

    Number getKwhExported();

    Number getKwhImported();

    Number getvL1L2();

    Number getvL1N();

    Number getvL2L3();

    Number getvL2N();

    Number getvL3L1();

    Number getvL3N();

    Number getVaTotal();

    Number getVarTotal();

    Number getwL1();

    Number getwL2();

    Number getwL3();

    Number getwTotal();
}
