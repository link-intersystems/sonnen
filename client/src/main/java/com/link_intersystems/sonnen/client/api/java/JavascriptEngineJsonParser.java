package com.link_intersystems.sonnen.client.api.java;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class JavascriptEngineJsonParser implements JsonParser {

    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> parseMap(String json) throws Exception {
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = scriptEngine.eval(script);
        return (Map<String, Object>) result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> parseList(String json) throws ScriptException {
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = scriptEngine.eval(script);
        return (List<Map<String, Object>>) result;
    }
}
