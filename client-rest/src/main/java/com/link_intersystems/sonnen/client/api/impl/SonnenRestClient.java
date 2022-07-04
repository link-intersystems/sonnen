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

import com.link_intersystems.sonnen.client.api.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * A {@link SonnenClient} based on pure Java.
 *
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class SonnenRestClient implements SonnenClient {

    private final RestClient restClient;

    public SonnenRestClient(RestClient restClient) {
        this.restClient = requireNonNull(restClient);
    }


    @Override
    public Latestdata getLatestdata() throws SonnenClientException {
        try {
            return restClient.getResource("latestdata", DefaultLatestdata::new);
        } catch (IOException | URISyntaxException e) {
            throw new SonnenClientException(e);
        }
    }

    @Override
    public Status getStatus() throws SonnenClientException {
        try {
            return restClient.getResource("status", DefaultStatus::new);
        } catch (IOException | URISyntaxException e) {
            throw new SonnenClientException(e);
        }
    }

    @Override
    public <T> T getConfiguration(Configuration<T> configuration) throws SonnenClientException {
        try {
            String resourceName = "configurations/" + configuration.getName();
            Map<String, Object> jsonObject = restClient.getResourceAsMap(resourceName);
            Object value = jsonObject.get(configuration.getName());
            return configuration.cast(value);
        } catch (IOException | URISyntaxException e) {
            throw new SonnenClientException(e);
        }
    }

    @Override
    public <T> void setConfiguration(Configuration<T> configuration, T value) throws SonnenClientException {
        try {
            restClient.putResource("configurations", outputStream -> {
                String name = configuration.getName();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write(name);
                writer.write("=");
                writer.write(String.valueOf(value));
                writer.flush();
            });
        } catch (IOException | URISyntaxException e) {
            throw new SonnenClientException(e);
        }
    }

    @Override
    public List<Powermeter> getPowermeter() throws SonnenClientException {
        try {
            return restClient.getListResource("powermeter", DefaultPowermeter::new);
        } catch (IOException | URISyntaxException e) {
            throw new SonnenClientException(e);
        }
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
        try {
            restClient.postResource("setpoint/" + name + "/" + watt);
        } catch (IOException | URISyntaxException e) {
            throw new SonnenClientException(e);
        }
    }


}
