package com.link_intersystems.sonnen.client.api;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface EclipseLed {
    Boolean getBlinkingRed();

    Boolean getPulsingGreen();

    Boolean getPulsingOrange();

    Boolean getPulsingWhite();

    Boolean getSolidRed();
}
