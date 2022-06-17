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

package com.link_intersystems.sonnen.datalogger.controller;

import com.link_intersystems.sonnen.client.api.SonnenClient;
import com.link_intersystems.sonnen.client.api.SonnenClientException;
import com.link_intersystems.sonnen.client.api.Status;
import com.link_intersystems.sonnen.datalogger.entity.SonnenRepository;

import static java.util.Objects.requireNonNull;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class StatusDataLoggerController {

    private SonnenClient sonnenClient;
    private SonnenRepository repository;

    public StatusDataLoggerController(SonnenClient sonnenClient, SonnenRepository repository) {
        this.sonnenClient = requireNonNull(sonnenClient);
        this.repository = requireNonNull(repository);
    }

    public void execute() {
        try {
            Status status = sonnenClient.getStatus();
            repository.persist(status);
        } catch (SonnenClientException e) {
            e.printStackTrace();
        }
    }
}
