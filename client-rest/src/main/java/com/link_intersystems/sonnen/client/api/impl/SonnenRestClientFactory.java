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

package com.link_intersystems.sonnen.client.api.impl;

import com.link_intersystems.net.http.HttpClient;
import com.link_intersystems.sonnen.client.api.SonnenClientFactory;
import com.link_intersystems.sonnen.client.api.SonnenClientProperties;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class SonnenRestClientFactory extends SonnenClientFactory {

    public SonnenRestClient create(SonnenClientProperties properties) {
        HttpClient httpClient = new HttpClient();
        JsonFormat jsonFormat = new JacksonJsonFormat();
        RestClient restClient = new RestClient(properties, httpClient, jsonFormat);
        return new SonnenRestClient(restClient);
    }
}
