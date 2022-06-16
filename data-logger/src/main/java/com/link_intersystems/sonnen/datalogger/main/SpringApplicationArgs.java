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
