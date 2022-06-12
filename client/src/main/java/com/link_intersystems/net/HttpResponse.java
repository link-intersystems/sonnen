package com.link_intersystems.net;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface HttpResponse {

    int getResponseCode() throws IOException;

    InputStream getContent() throws IOException;
}
