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

import com.link_intersystems.net.HttpClient;
import com.link_intersystems.net.HttpResponse;
import com.link_intersystems.net.java.OngoingOutput;
import com.link_intersystems.sonnen.client.api.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * A {@link SonnenClient} based on pure Java.
 *
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class JavaSonnenClient implements SonnenClient {

    private static final Logger LOGGER = Logger.getLogger(JavaSonnenClient.class.getName());

    private Charset contentCharset = StandardCharsets.UTF_8;
    private String acceptMimeType = "application/json";
    private JsonFormat jsonFormat;

    private final SonnenClientProperties properties;
    private HttpClient httpClient;

    public JavaSonnenClient(SonnenClientProperties properties, HttpClient httpClient, JsonFormat jsonFormat) {
        this.httpClient = requireNonNull(httpClient);
        this.jsonFormat = requireNonNull(jsonFormat);
        this.properties = requireNonNull(properties);
    }

    public void setContentCharset(Charset contentCharset) {
        this.contentCharset = requireNonNull(contentCharset);
    }

    public Charset getContentCharset() {
        return contentCharset;
    }

    public void setAcceptMimeType(String acceptMimeType) {
        this.acceptMimeType = Objects.requireNonNull(acceptMimeType);
    }

    public String getAcceptMimeType() {
        return acceptMimeType;
    }

    @Override
    public Latestdata getLatestdata() throws SonnenClientException {
        return getMapResource("latestdata", DefaultLatestdata::new);
    }

    @Override
    public Status getStatus() throws SonnenClientException {
        return getMapResource("status", DefaultStatus::new);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getConfiguration(Configuration<T> configuration) throws SonnenClientException {
        String resourceName = "configurations/" + configuration.getName();
        Map<String, Object> jsonObject = getMapResource(resourceName);
        return (T) jsonObject.get(configuration.getName());
    }

    @Override
    public <T> void setConfiguration(Configuration<T> configuration, T value) throws SonnenClientException {
        try {
            String name = configuration.getName();
            URL url = getResourceUrl("configurations");

            Map<String, String> headers = getHeaders();
            headers.remove("Accept");
            OngoingOutput ongoingPut = httpClient.put(url, headers);

            OutputStream outputStream = ongoingPut.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(name);
            writer.write("=");
            writer.write(String.valueOf(value));
            writer.flush();


            LOGGER.log(Level.FINE, "Put URL {0}", url);

            HttpResponse response = ongoingPut.close();

            int responseCode = response.getResponseCode();

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "Response {0} : {1}", new Object[]{url, responseCode});
            }

            if (responseCode != 200) {
                throw new RuntimeException("Connection to " + url + " failed with response code: " + responseCode);
            }

        } catch (Exception e) {
            throw new SonnenClientException(e);
        }
    }

    @Override
    public List<Powermeter> getPowermeter() throws SonnenClientException {
        return getListResource("powermeter", DefaultPowermeter::new);
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
            URL url = getResourceUrl("setpoint/" + name + "/" + watt);

            Map<String, String> headers = getHeaders();
            OngoingOutput ongoingPost = httpClient.post(url, headers);


            LOGGER.log(Level.FINE, "Post URL {0}", url);

            HttpResponse response = ongoingPost.close();

            int responseCode = response.getResponseCode();

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "Response {0} : {1}", new Object[]{url, responseCode});
            }

            if (responseCode != 201) {
                throw new RuntimeException("Connection to " + url + " failed with response code: " + responseCode);
            }

        } catch (Exception e) {
            throw new SonnenClientException(e);

        }
    }


    private String getResource(String resourceName) throws SonnenClientException {
        try {
            URL url = getResourceUrl(resourceName);


            Map<String, String> headers = getHeaders();
            HttpResponse response = httpClient.get(url, headers);

            LOGGER.log(Level.FINE, "Get URL {0}", url);

            int responseCode = response.getResponseCode();

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "Response {0} : {1}", new Object[]{url, responseCode});
            }

            if (responseCode != 200) {
                throw new RuntimeException("Connection to " + url + " failed with response code: " + responseCode);
            }

            try (BufferedReader reader = getReader(response)) {
                return read(reader);
            }
        } catch (Exception e) {
            throw new SonnenClientException(e);

        }
    }

    private <T> T getMapResource(String resourceName, Function<Map<String, Object>, T> constructor) throws SonnenClientException {
        try {
            Map<String, Object> mapData = getMapResource(resourceName);
            return constructor.apply(mapData);
        } catch (Exception e) {
            throw new SonnenClientException(e);

        }
    }

    private Map<String, Object> getMapResource(String resourceName) throws SonnenClientException {
        String json = getResource(resourceName);
        try {
            return jsonFormat.parseObject(json);
        } catch (Exception e) {
            throw new SonnenClientException(e);

        }
    }

    private <T> List<T> getListResource(String resourceName, Function<Map<String, Object>, T> elementConstructor) throws SonnenClientException {
        String json = getResource(resourceName);
        try {
            List<Map<String, Object>> objects = jsonFormat.parseArray(json);
            return objects.stream().map(elementConstructor).collect(Collectors.toList());
        } catch (Exception e) {
            throw new SonnenClientException(e);

        }
    }

    private URL getResourceUrl(String status) throws URISyntaxException, MalformedURLException {
        URI apiUri = new URI(properties.getApiUri());
        URI latestDataUri = apiUri.resolve(new URI(status));
        return latestDataUri.toURL();
    }

    protected BufferedReader getReader(HttpResponse response) throws IOException {
        return new BufferedReader(new InputStreamReader(response.getContent(), contentCharset));
    }

    protected Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();

        headers.put("Accept", acceptMimeType);
        headers.put("Auth-Token", properties.getApiToken());

        return headers;
    }


    protected String read(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder(8192);

        char[] buff = new char[8192];
        int read;
        while ((read = reader.read(buff)) > 0) {
            sb.append(buff, 0, read);
        }

        return sb.toString();
    }

}
