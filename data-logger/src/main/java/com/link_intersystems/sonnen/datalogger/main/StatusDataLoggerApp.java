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

import com.link_intersystems.net.java.JavaHttpClient;
import com.link_intersystems.sonnen.client.api.java.JacksonJsonFormat;
import com.link_intersystems.sonnen.client.api.java.JavaSonnenClient;
import com.link_intersystems.sonnen.client.api.SonnenClientProperties;
import com.link_intersystems.sonnen.datalogger.controller.StatusDataLoggerController;
import com.link_intersystems.sonnen.datalogger.entity.SonnenRepository;
import com.link_intersystems.sonnen.datalogger.gateway.MongoDBConfiguration;
import com.link_intersystems.sonnen.datalogger.gateway.MongoSonnenRepository;
import com.mongodb.client.MongoClient;
import org.apache.commons.cli.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.ZoneId;
import java.util.Optional;
import java.util.Properties;

import static com.link_intersystems.sonnen.datalogger.main.ApplicationArgs.*;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
@SpringBootApplication
@EnableConfigurationProperties({SpringSonnenClientProperties.class})
public class StatusDataLoggerApp {

    public static void main(String[] args) throws ParseException {
        try {
            CommandLine cmd = parseCommandLine(args);
            if (cmd.hasOption(HELP)) {
                printHelp();
            }
        } catch (ParseException e) {
            printHelp();
        }

        SpringApplication.run(StatusDataLoggerApp.class, args);
    }

    public static CommandLine parseCommandLine(String[] args) throws ParseException {
        DefaultParser parser = new DefaultParser();
        Options options = getOptions();
        return parser.parse(options, args, new Properties(), true);
    }

    public static Options getOptions() {
        Options options = new Options();

        options.addOption(HELP, false, "Print this help.");
        options.addOption(RUN_INFINITELY_ARG, false, "Run the application infinitely.");
        options.addOption(RUN_COUNT, true, "The count of sonnenBatterie status to retrieve. Will be overriden by -f.");
        options.addOption(SLEEP_DURATION, true, "The time to sleep between status requests. The format is defined by java.time.Duration.parse(). E.g. PT15M for 15 minutes or PT1.000S for 1 second.");
        options.addOption(COLLECTION_NAME, true, "The mongodb collection name to persist the data to. Default is 'status'.");
        options.addOption(DATABASE_NAME, true, "The mongodb database name to persist the data to. Default is 'sonnen'.");


        return options;
    }


    private static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        Options options = getOptions();
        formatter.printHelp("java -jar app.jar", options);
        System.exit(0);
    }


    @Bean
    public ApplicationArgs applicationArgs(ApplicationArguments arguments) throws ParseException {
        CommandLine cmd = parseCommandLine(arguments.getSourceArgs());
        return new CommonsCliApplicationArgs(cmd);
    }

    @Bean
    public MongoDBConfiguration mongoDBConfiguration(ApplicationArgs applicationArgs) {
        return new MongoDBConfiguration() {
            @Override
            public String getStatusCollectionName() {
                return applicationArgs.getOption(COLLECTION_NAME, "status");
            }

            @Override
            public ZoneId getTimeZoneId() {
                Optional<String> zone = applicationArgs.getOption(ApplicationArgs.TIME_ZONE);
                return zone.map(ZoneId::of).orElseGet(ZoneId::systemDefault);
            }
        };
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory, MongoClient mongoClient, ApplicationArgs applicationArgs) {
        Optional<String> databaseNameArg = applicationArgs.getOption(DATABASE_NAME);
        return databaseNameArg
                .map(databaseName -> new MongoTemplate(mongoClient, databaseName))
                .orElseGet(() -> new MongoTemplate(mongoDatabaseFactory));
    }

    @Bean
    public SonnenRepository sonnenRepository(MongoTemplate mongoTemplate, MongoDBConfiguration mongoDBConfiguration) {
        return new MongoSonnenRepository(mongoDBConfiguration, mongoTemplate);
    }

    @Bean
    public StatusDataLoggerController dataLoggerController(SonnenClientProperties sonnenClientProperties, SonnenRepository sonnenRepository) {
        JavaSonnenClient javaSonnenClient = new JavaSonnenClient(sonnenClientProperties, new JavaHttpClient(), new JacksonJsonFormat());
        return new StatusDataLoggerController(javaSonnenClient, sonnenRepository);
    }

    @Bean
    public ApplicationRunner commandLineRunner(StatusDataLoggerController dataLogger, ApplicationArgs applicationArgs) {
        return new StatusDataLoggerRunner(dataLogger, applicationArgs);
    }

}
