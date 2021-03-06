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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.util.Optional;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class CommonsCliApplicationArgs implements ApplicationArgs {

    private CommandLine cmd;

    public CommonsCliApplicationArgs(CommandLine cmd) {
        this.cmd = cmd;
    }

    @Override
    public Optional<String> getOption(String optionName) {
        return Optional.ofNullable(cmd.getOptionValue(optionName));
    }

    @Override
    public boolean isOptionEnabled(String optionName) {
        return cmd.hasOption(optionName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Command Line Options:\n");

        Option[] options = cmd.getOptions();
        for (Option option : options) {
            sb.append("\to ");
            sb.append(option.getOpt());
            sb.append("=");
            if (option.hasArg()) {
                String optionValue = cmd.getOptionValue(option);
                sb.append(optionValue);
            } else {
                sb.append(cmd.hasOption(option));
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
