package com.link_intersystems.net;

import com.link_intersystems.net.java.OngoingOutput;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface HttpClient {

    HttpResponse get(URL url, Map<String, String> requestHeader) throws IOException;

    OngoingOutput post(URL url, Map<String, String> requestHeader) throws IOException;

    OngoingOutput put(URL url, Map<String, String> requestHeader) throws IOException;
}
