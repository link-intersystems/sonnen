package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.Latestdata;

import java.io.Reader;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface ContentHandler {

    Latestdata parseLatestData(Reader reader) throws Exception;
}
