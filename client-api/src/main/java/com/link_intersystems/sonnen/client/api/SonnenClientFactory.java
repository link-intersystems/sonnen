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

package com.link_intersystems.sonnen.client.api;

import java.util.Iterator;
import java.util.ServiceLoader;

import static java.text.MessageFormat.format;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public abstract class SonnenClientFactory {

    public static SonnenClientFactory getInstance() {
        ServiceLoader<SonnenClientFactory> serviceLoader = ServiceLoader.load(SonnenClientFactory.class);

        Iterator<SonnenClientFactory> iterator = serviceLoader.iterator();
        if (!iterator.hasNext()) {
            String msg = format("Unable to find a {0}. Add a provider to the classpath (META-INF/services/{1}).",
                    SonnenClientFactory.class.getSimpleName(),
                    SonnenClientFactory.class.getName()
            );
            throw new IllegalStateException(msg);
        }

        SonnenClientFactory sonnenClientFactory = iterator.next();
        if (iterator.hasNext()) {
            String msg = format("Can not determine a {0} provider. Multiple provider on the classpath (META-INF/services/{1}).",
                    SonnenClientFactory.class.getSimpleName(),
                    SonnenClientFactory.class.getName()
            );
            throw new IllegalStateException(msg);
        }

        return sonnenClientFactory;
    }

    public SonnenClient create(String apiUri, String apiToken) {
        DefaultSonnenClientProperties sonnenClientProperties = new DefaultSonnenClientProperties();

        sonnenClientProperties.setApiUrl(apiUri);
        sonnenClientProperties.setApiToken(apiToken);

        return create(sonnenClientProperties);
    }

    public abstract SonnenClient create(SonnenClientProperties properties);

}
