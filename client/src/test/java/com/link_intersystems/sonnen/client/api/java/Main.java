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
import com.link_intersystems.sonnen.client.api.*;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class Main {


    /**
     * @param args pass the api url as the first argument and the api token as the second.
     */
    public static void main(String[] args) throws SonnenClientException {
        DefaultSonnenClientProperties properties = new DefaultSonnenClientProperties();
        properties.setApiUrl(args[0]);
        properties.setApiToken(args[1]);

        SonnenRestClient restClient = new SonnenRestClient(properties, new HttpClient(), new JacksonJsonFormat());
        SonnenClient sonnenClient = new DefaultSonnenClient(restClient);

        Status status = sonnenClient.getStatus();
        Latestdata latestdata = sonnenClient.getLatestdata();

        System.out.println("Erzeugung [W]: " + status.getProductionW());
        System.out.println("Verbrauch [W]: " + status.getConsumptionW());
        System.out.println("Einspeisung [W]: " + status.getGridFeedInW());
        System.out.println("Batterie [W]: " + (status.getPacTotalW()));
        System.out.println("Notstromreserve [%]: " + status.getBackupBuffer());
        System.out.println("Maximale Ladekapazität [W]: " + latestdata.getFullChargeCapacity());

        System.out.println("Batteriestrom verwendet: " + status.getFlowConsumptionBattery());
        System.out.println("Netzstrom verwendet: " + status.getFlowConsumptionGrid());
        System.out.println("Batterieladen von Netz: " + status.getFlowGridBattery());
        System.out.println("Batterieladen von PV: " + status.getFlowProductionBattery());
        System.out.println("Stromverbrauch vob PV: " + status.getFlowConsumptionProduction());
        System.out.println("Einspeisung vob PV: " + status.getFlowProductionGrid());
    }


}
