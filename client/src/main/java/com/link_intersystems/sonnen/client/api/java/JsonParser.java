package com.link_intersystems.sonnen.client.api.java;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface JsonParser {

    Map<String, Object> parseObject(String json) throws Exception;

    List<Map<String, Object>> parseArray(String json) throws IOException, ScriptException;

}