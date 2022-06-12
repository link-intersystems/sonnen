package com.link_intersystems.sonnen.client.api.java;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class DefaultSonnenClientProperties implements SonnenClientProperties {

    private String apiToken;
    private String apiUrl;

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public String getApiUri() {
        return apiUrl;
    }

    @Override
    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

}
