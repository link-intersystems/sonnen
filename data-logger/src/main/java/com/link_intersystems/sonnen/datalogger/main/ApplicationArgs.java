package com.link_intersystems.sonnen.datalogger.main;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface ApplicationArgs {

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
