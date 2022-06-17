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

import com.link_intersystems.sonnen.client.api.java.JavaSonnenClient;
import com.link_intersystems.sonnen.client.api.java.SonnenClientProperties;
import com.link_intersystems.sonnen.datalogger.controller.StatusDataLoggerController;
import com.link_intersystems.sonnen.datalogger.entity.SonnenRepository;
import com.link_intersystems.sonnen.datalogger.gateway.MongoDBConfiguration;
import com.link_intersystems.sonnen.datalogger.gateway.MongoSonnenRepository;
import com.mongodb.client.MongoClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.ZoneId;
import java.util.Optional;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
@SpringBootApplication
@EnableConfigurationProperties({SpringSonnenClientProperties.class})
public class StatusDataLoggerApp {

    public static final String DEFAULT_DATABASE_NAME = "sonnen";

    public static void main(String[] args) {
        SpringApplication.run(StatusDataLoggerApp.class, args);
    }

    @Bean
    public ApplicationArgs applicationArgs(ApplicationArguments arguments) {
        return new SpringApplicationArgs(arguments);
    }

    @Bean
    public MongoDBConfiguration mongoDBConfiguration(ApplicationArgs applicationArgs) {
        return new MongoDBConfiguration() {
            @Override
            public String getStatusCollectionName() {
                return applicationArgs.getOption("collectionName", "status");
            }

            @Override
            public ZoneId getTimeZoneId() {
                Optional<String> zone = applicationArgs.getOption("zone");
                return zone.map(ZoneId::of).orElseGet(ZoneId::systemDefault);
            }
        };
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient, ApplicationArgs applicationArgs) {
        String databaseName = applicationArgs.getOption("databaseName", DEFAULT_DATABASE_NAME);
        return new MongoTemplate(mongoClient, databaseName);
    }

    @Bean
    public SonnenRepository sonnenRepository(MongoTemplate mongoTemplate, MongoDBConfiguration mongoDBConfiguration) {
        return new MongoSonnenRepository(mongoDBConfiguration, mongoTemplate);
    }

    @Bean
    public StatusDataLoggerController dataLoggerController(SonnenClientProperties sonnenClientProperties, SonnenRepository sonnenRepository) {
        JavaSonnenClient javaSonnenClient = new JavaSonnenClient(sonnenClientProperties);
        return new StatusDataLoggerController(javaSonnenClient, sonnenRepository);
    }

    @Bean
    public ApplicationRunner commandLineRunner(StatusDataLoggerController dataLogger) {
        return new StatusDataLoggerRunner(dataLogger);
    }

}
