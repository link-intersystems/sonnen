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

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface ApplicationArgs {

    public static final String RUN_INFINITELY_ARG = "f";
    public static final String RUN_COUNT = "c";
    public static final String SLEEP_DURATION = "s";
    public static final String COLLECTION_NAME = "n";
    public static final String TIME_ZONE = "z";
    public static final String HELP = "h";

    default public String getOption(String optionName, String defaultValue) {
        return getOption(optionName).orElse(defaultValue);
    }

    default public String getOption(String optionName, Supplier<String> defaultValueSupplier) {
        return getOption(optionName).orElseGet(defaultValueSupplier);
    }

    default public void ifOption(String optName, Consumer<String> optValueConsumer) {
        Optional<String> option = getOption(optName);
        option.ifPresent(optValueConsumer);
    }

    public Optional<String> getOption(String optionName);

    boolean isOptionEnabled(String optionName);
}
