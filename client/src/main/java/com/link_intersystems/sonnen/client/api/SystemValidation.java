package com.link_intersystems.sonnen.client.api;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface SystemValidation {
    Boolean getCountryCodeSetStatusFlag1();

    Boolean getCountryCodeSetStatusFlag2();

    Boolean getSelfTestErrorDCWiring();

    Boolean getSelfTestPostponed();

    Boolean getSelfTestPreconditionNotMet();

    Boolean getSelfTestRunning();

    Boolean getSelfTestSuccessfulFinished();
}
