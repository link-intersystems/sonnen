package com.link_intersystems.net.java;

import com.link_intersystems.net.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface OngoingOutput {

    OutputStream getOutputStream() throws IOException;

    HttpResponse close() throws IOException;
}
