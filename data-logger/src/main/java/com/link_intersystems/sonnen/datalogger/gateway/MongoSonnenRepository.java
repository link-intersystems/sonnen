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

package com.link_intersystems.sonnen.datalogger.gateway;

import com.link_intersystems.sonnen.client.api.Status;
import com.link_intersystems.sonnen.datalogger.entity.SonnenRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class MongoSonnenRepository implements SonnenRepository {

    private EntityPersistenceConverter persistenceConverter;

    private MongoTemplate mongoTemplate;
    private MongoDBConfiguration mongoDBConfiguration;

    public MongoSonnenRepository(MongoDBConfiguration mongoDBConfiguration, MongoTemplate mongoTemplate) {
        this.mongoDBConfiguration = requireNonNull(mongoDBConfiguration);
        this.mongoTemplate = requireNonNull(mongoTemplate);
        persistenceConverter = new EntityPersistenceConverter(mongoDBConfiguration.getTimeZoneId());
    }

    @Override
    public void persist(Status status) {
        Map<String, Object> mapToSave = persistenceConverter.convert(status);
        String statusCollectionName = mongoDBConfiguration.getStatusCollectionName();
        mongoTemplate.insert(mapToSave, statusCollectionName);
    }
}
