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

package com.link_intersystems.sonnen.datalogger.main;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class SonnenDataLoggerArgs {

    private ApplicationArgs applicationArgs;

    public SonnenDataLoggerArgs(ApplicationArgs applicationArgs) {
        this.applicationArgs = applicationArgs;
    }

    public boolean isInfinite() {
        return applicationArgs.isOptionEnabled("f");
    }

    public int getMaxIterations() {
        return applicationArgs.getOption("c").map(Integer::parseInt).orElse(5);
    }

    public long getLagDurationMs() {
        return applicationArgs.getOption("s").map(this::parseDurationOption).map(Duration::toMillis).orElse(1000L);
    }

    private Duration parseDurationOption(String value) {
        try {
            return Duration.parse(value);
        } catch (DateTimeParseException e) {
            Integer millis = Integer.valueOf(value);
            return Duration.of(millis, ChronoUnit.MILLIS);
        }
    }
}
