package com.link_intersystems.sonnen.datalogger.main;

import com.link_intersystems.sonnen.client.api.DefaultSonnenClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
@ConfigurationProperties("spring.sonnen")
public class SpringSonnenClientProperties extends DefaultSonnenClientProperties {
}
