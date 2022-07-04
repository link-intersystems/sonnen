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

import com.link_intersystems.net.http.ContentWriter;
import com.link_intersystems.net.http.HttpClient;
import com.link_intersystems.net.http.HttpHeaders;
import com.link_intersystems.net.http.HttpResponse;
import com.link_intersystems.sonnen.client.api.SonnenClientProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class RestClient {

    private static final Logger LOGGER = Logger.getLogger(RestClient.class.getName());

    private final SonnenClientProperties properties;
    private Charset contentCharset = StandardCharsets.UTF_8;
    private String acceptMimeType = "application/json";
    private JsonFormat jsonFormat;
    private HttpClient httpClient;

    public RestClient(SonnenClientProperties properties, HttpClient httpClient, JsonFormat jsonFormat) {
        this.properties = requireNonNull(properties);
        this.httpClient = requireNonNull(httpClient);
        this.jsonFormat = requireNonNull(jsonFormat);
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

    public String getResourceJson(String resourceName) throws IOException, URISyntaxException {
        URL url = getResourceUrl(resourceName);

        HttpHeaders headers = getHeaders();

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
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Accept", acceptMimeType);
        httpHeaders.add("Auth-Token", properties.getApiToken());

        return httpHeaders;

    }

    public <T> T getResource(String resourceName, Function<Map<String, Object>, T> constructor) throws IOException, URISyntaxException {
        Map<String, Object> mapData = getResourceAsMap(resourceName);
        return constructor.apply(mapData);
    }

    public Map<String, Object> getResourceAsMap(String resourceName) throws IOException, URISyntaxException {
        String json = getResourceJson(resourceName);
        return jsonFormat.parseObject(json);
    }

    public <T> List<T> getListResource(String resourceName, Function<Map<String, Object>, T> elementConstructor) throws IOException, URISyntaxException {
        String json = getResourceJson(resourceName);
        List<Map<String, Object>> objects = jsonFormat.parseArray(json);
        return objects.stream().map(elementConstructor).collect(Collectors.toList());
    }

    public void postResource(String resourceName) throws IOException, URISyntaxException {
        URL url = getResourceUrl(resourceName);

        LOGGER.log(Level.FINE, "Post URL {0}", url);

        HttpResponse response = httpClient.post(url, getHeaders());

        int responseCode = response.getResponseCode();

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Response {0} : {1}", new Object[]{url, responseCode});
        }

        if (responseCode != 201) {
            throw new RuntimeException("Connection to " + url + " failed with response code: " + responseCode);
        }
    }

    public void putResource(String resourceName, ContentWriter contentWriter) throws IOException, URISyntaxException {
        URL url = getResourceUrl(resourceName);

        LOGGER.log(Level.FINE, "Put URL {0}", url);

        HttpResponse response = httpClient.put(url, contentWriter);

        int responseCode = response.getResponseCode();

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Response {0} : {1}", new Object[]{url, responseCode});
        }

        if (responseCode != 200) {
            throw new RuntimeException("Connection to " + url + " failed with response code: " + responseCode);
        }

    }

    protected URL getResourceUrl(String resourceName) throws URISyntaxException, MalformedURLException {
        URI apiUri = new URI(properties.getApiUri());
        URI resourceUri = apiUri.resolve(new URI(resourceName));
        return resourceUri.toURL();
    }

    protected BufferedReader getReader(HttpResponse response) throws IOException {
        return new BufferedReader(new InputStreamReader(response.getContent(), contentCharset));
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
