/*
 * Copyright (C) 2022 Link Intersystems GmbH
 *
 * This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *   SPDX-License-Identifier: EPL-2.0
 *
 *   Contributors:
 *       Link Intersystems GmbH - initial API and implementation
 */

package com.link_intersystems.sonnen.client.api.java;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;
import java.util.Map;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class JavascriptEngineJsonParser implements JsonParser {

    private ScriptEngine scriptEngine;

    public JavascriptEngineJsonParser() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        scriptEngine = scriptEngineManager.getEngineByName("javascript");
        if (scriptEngine == null) {
            throw new IllegalStateException("ScriptEnginge 'javascript' is not available");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> parseObject(String json) throws Exception {
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = scriptEngine.eval(script);
        return (Map<String, Object>) result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> parseArray(String json) throws ScriptException {
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = scriptEngine.eval(script);
        return (List<Map<String, Object>>) result;
    }
}
