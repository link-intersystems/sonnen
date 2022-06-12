package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.Configuration;
import com.link_intersystems.sonnen.client.api.Latestdata;
import com.link_intersystems.sonnen.client.api.Powermeter;
import com.link_intersystems.sonnen.client.api.Status;

import java.io.Reader;
import java.util.List;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface ContentHandler {

    Latestdata parseLatestData(Reader reader) throws Exception;

    Status parseStatus(Reader reader) throws Exception;

    <T> T parseConfiguration(Reader reader, Configuration<T> configuration) throws Exception;

    List<Powermeter> parsePowermeter(Reader reader) throws Exception;
}