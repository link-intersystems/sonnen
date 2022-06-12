package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.Latestdata;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class JavascriptEngineContentHandler implements ContentHandler {

    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");

    @Override
    public Latestdata parseLatestData(Reader reader) throws Exception {
        Map<String, Object> contents = parseJSON(reader);
        return new JsonMapLatestdata(contents);
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> parseJSON(Reader reader) throws IOException, ScriptException {
        CharSequence json = read(reader);
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = scriptEngine.eval(script);
        return (Map<String, Object>) result;
    }

    protected CharSequence read(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder(8192);

        char[] buff = new char[8192];
        int read;
        while ((read = reader.read(buff)) > 0) {
            sb.append(buff, 0, read);
        }

        return sb.toString();
    }
}
