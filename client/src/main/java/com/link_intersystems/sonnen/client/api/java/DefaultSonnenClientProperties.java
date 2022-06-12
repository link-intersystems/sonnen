package com.link_intersystems.sonnen.client.api.java;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class DefaultSonnenClientProperties implements SonnenClientProperties {

    private String authToken;
    private String apiUrl;

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public String getApiUri() {
        return apiUrl;
    }

    @Override
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

}
