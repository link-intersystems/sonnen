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

import com.link_intersystems.sonnen.client.api.*;

import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * A {@link SonnenClient} based on pure Java.
 *
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class DefaultSonnenClient implements SonnenClient {

    private final SonnenRestClient restClient;

    public DefaultSonnenClient(SonnenRestClient restClient) {
        this.restClient = requireNonNull(restClient);
    }


    @Override
    public Latestdata getLatestdata() throws SonnenClientException {
        return restClient.getResource("latestdata", DefaultLatestdata::new);
    }

    @Override
    public Status getStatus() throws SonnenClientException {
        return restClient.getResource("status", DefaultStatus::new);
    }

    @Override
    public <T> T getConfiguration(Configuration<T> configuration) throws SonnenClientException {
        String resourceName = "configurations/" + configuration.getName();
        Map<String, Object> jsonObject = restClient.getResourceAsMap(resourceName);
        Object value = jsonObject.get(configuration.getName());
        return configuration.cast(value);
    }

    @Override
    public <T> void setConfiguration(Configuration<T> configuration, T value) throws SonnenClientException {
        restClient.putResource("configurations", outputStream -> {
            String name = configuration.getName();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(name);
            writer.write("=");
            writer.write(String.valueOf(value));
            writer.flush();
        });
    }

    @Override
    public List<Powermeter> getPowermeter() throws SonnenClientException {
        return restClient.getListResource("powermeter", DefaultPowermeter::new);
    }

    @Override
    public void discharge(int watt) throws SonnenClientException {
        postSetpoint("discharge", watt);
    }

    @Override
    public void charge(int watt) throws SonnenClientException {
        postSetpoint("charge", watt);
    }

    public void postSetpoint(String name, int watt) throws SonnenClientException {
        restClient.postResource("setpoint/" + name + "/" + watt);
    }


}
