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

import org.springframework.boot.ApplicationArguments;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class SpringApplicationArgs implements ApplicationArgs {

    private ApplicationArguments arguments;

    public SpringApplicationArgs(ApplicationArguments arguments) {
        this.arguments = requireNonNull(arguments);
    }

    public Optional<String> getOption(String optionName) {
        List<String> optionArgs = Optional.ofNullable(arguments.getOptionValues(optionName)).orElseGet(Collections::emptyList);
        return optionArgs.stream().findFirst();
    }

    @Override
    public boolean isOptionEnabled(String optionName) {
        Set<String> optionNames = arguments.getOptionNames();
        return optionNames.contains(optionName);
    }


}
