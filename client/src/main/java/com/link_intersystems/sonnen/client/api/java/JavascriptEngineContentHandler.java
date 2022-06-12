package com.link_intersystems.sonnen.client.api.java;

import com.link_intersystems.sonnen.client.api.Configuration;
import com.link_intersystems.sonnen.client.api.Latestdata;
import com.link_intersystems.sonnen.client.api.Powermeter;
import com.link_intersystems.sonnen.client.api.Status;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Status parseStatus(Reader reader) throws Exception {
        Map<String, Object> contents = parseJSON(reader);
        return new JsonMapStatus(contents);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T parseConfiguration(Reader reader, Configuration<T> configuration) throws Exception {
        Map<String, Object> contents = parseJSON(reader);
        return (T) contents.get(configuration.getName());
    }

    @Override
    public List<Powermeter> parsePowermeter(Reader reader) throws Exception {
        List<Map<String, Object>> contents = parseJSONList(reader);
        return contents.stream().map(JsonMapPowermeter::new).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    protected List<Map<String, Object>> parseJSONList(Reader reader) throws IOException, ScriptException {
        CharSequence json = read(reader);
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = scriptEngine.eval(script);
        return (List<Map<String, Object>>) result;
    }


    @SuppressWarnings("unchecked")
    protected Map<String, Object> parseJSON(Reader reader) throws IOException, ScriptException {
        CharSequence json = read(reader);
        System.out.println(json);
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
