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