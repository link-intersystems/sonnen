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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * A {@link SonnenClient} based on pure Java.
 *
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class JavaSonnenClient implements SonnenClient {


    private Charset contentCharset = StandardCharsets.UTF_8;
    private String acceptMimeType = "application/json";

    private final SonnenClientProperties properties;
    private JsonParser jsonParser;
    private HttpClient httpClient;

    public JavaSonnenClient(SonnenClientProperties properties) {
        this(properties, new JavaHttpClient(), new JavascriptEngineJsonParser());
    }

    public JavaSonnenClient(SonnenClientProperties properties, HttpClient httpClient, JsonParser jsonParser) {
        this.httpClient = requireNonNull(httpClient);
        this.jsonParser = requireNonNull(jsonParser);
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
        return getMapResource("latestdata", jo -> new JsonLatestdata(jo.getData(), jo.getJson()));
    }

    @Override
    public Status getStatus() throws SonnenClientException {
        return getMapResource("status", jo -> new JsonStatus(jo.getData(), jo.getJson()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getConfiguration(Configuration<T> configuration) throws SonnenClientException {
        String resourceName = "configurations/" + configuration.getName();
        JsonObject<Map<String, Object>> jsonObject = getMapResource(resourceName);
        return (T) jsonObject.getData().get(configuration.getName());
    }

    @Override
    public List<Powermeter> getPowermeter() throws SonnenClientException {
        return getListResource("status", jo -> new JsonPowermeter(jo.getData()));
    }

    private String getResource(String resourceName) throws SonnenClientException {
        try {
            URL url = getResourceUrl(resourceName);

            Map<String, String> headers = getHeaders();
            HttpResponse response = httpClient.get(url, headers);

            int responseCode = response.getResponseCode();
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

    private <T> T getMapResource(String resourceName, Function<JsonObject<Map<String, Object>>, T> constructor) throws SonnenClientException {
        try {
            JsonObject<Map<String, Object>> jsonObject = getMapResource(resourceName);
            return constructor.apply(jsonObject);
        } catch (Exception e) {
            throw new SonnenClientException(e);

        }
    }

    private JsonObject<Map<String, Object>> getMapResource(String resourceName) throws SonnenClientException {
        String json = getResource(resourceName);
        try {
            Map<String, Object> map = jsonParser.parseMap(json);
            return new JsonObject<>(json, map);
        } catch (Exception e) {
            throw new SonnenClientException(e);

        }
    }

    private <T> List<T> getListResource(String resourceName, Function<JsonObject<Map<String, Object>>, T> elementConstructor) throws SonnenClientException {
        String json = getResource(resourceName);
        try {
            List<Map<String, Object>> objects = jsonParser.parseList(json);
            return objects.stream().map(o -> elementConstructor.apply(new JsonObject<>(json, o))).collect(Collectors.toList());
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
