package com.link_intersystems.net.java;

import com.link_intersystems.net.HttpClient;
import com.link_intersystems.net.HttpResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class JavaHttpClient implements HttpClient {

    @Override
    public HttpResponse get(URL url, Map<String, String> requestHeader) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        for (Map.Entry<String, String> requestHeaderEntry : requestHeader.entrySet()) {
            String headerName = requestHeaderEntry.getKey();
            String headerValue = requestHeaderEntry.getValue();
            conn.setRequestProperty(headerName, headerValue);
        }

        return new HttpUrlConnectionResponse(conn);
    }
}
