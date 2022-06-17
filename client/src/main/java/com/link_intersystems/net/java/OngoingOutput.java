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
