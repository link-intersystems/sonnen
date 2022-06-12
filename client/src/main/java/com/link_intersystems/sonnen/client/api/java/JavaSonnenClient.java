package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.net.HttpClient;
import com.link_intersystems.net.HttpResponse;
import com.link_intersystems.net.java.JavaHttpClient;
import com.link_intersystems.sonnen.client.api.*;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * A {@link SonnenClient} based on pure Java.
 *
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class JavaSonnenClient implements SonnenClient {

    private interface ContentParser<T> {
        T parse(Reader reader) throws Exception;
    }

    private Charset contentCharset = StandardCharsets.UTF_8;
    private String acceptMimeType = "application/json";

    private final SonnenClientProperties properties;
    private ContentHandler handler;
    private HttpClient httpClient;

    public JavaSonnenClient(SonnenClientProperties properties) {
        this(properties, new JavascriptEngineContentHandler(), new JavaHttpClient());
    }

    public JavaSonnenClient(SonnenClientProperties properties, ContentHandler handler, HttpClient httpClient) {
        this.httpClient = httpClient;
        this.handler = requireNonNull(handler);
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
        return getResource("latestdata", handler::parseLatestData);
    }

    @Override
    public Status getStatus() throws SonnenClientException {
        return getResource("status", handler::parseStatus);
    }

    @Override
    public <T> T getConfiguration(Configuration<T> configuration) throws SonnenClientException {
        String resourceName = "configurations/" + configuration.getName();
        return getResource(resourceName, r -> handler.parseConfiguration(r, configuration));
    }

    private <T> T getResource(String resourceName, ContentParser<T> contentParser) throws SonnenClientException {
        try {
            URL url = getResourceUrl(resourceName);

            Map<String, String> headers = getHeaders();
            HttpResponse response = httpClient.get(url, headers);

            int responseCode = response.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Connection to " + url + " failed with response code: " + responseCode);
            }

            try (BufferedReader reader = getReader(response)) {
                return contentParser.parse(reader);
            }
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
        headers.put("Auth-Token", properties.getAuthToken());

        return headers;
    }

}
