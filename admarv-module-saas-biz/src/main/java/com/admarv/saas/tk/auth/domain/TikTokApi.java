package com.admarv.saas.tk.auth.domain;

import java.io.OutputStream;

import com.github.scribejava.apis.facebook.FacebookAccessTokenJsonExtractor;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;
import com.github.scribejava.core.oauth2.clientauthentication.RequestBodyAuthenticationScheme;

/**
 * 
 * @author liuliu
 *
 */
public class TikTokApi extends DefaultApi20 {

    private final String version;

    protected TikTokApi() {
        this("2");
    }

    protected TikTokApi(String version) {
        this.version = version;
    }

    private static class InstanceHolder {
        private static final TikTokApi INSTANCE = new TikTokApi();
    }

    public static TikTokApi instance() {
        return InstanceHolder.INSTANCE;
    }

    public static TikTokApi customVersion(String version) {
        return new TikTokApi(version);
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }
    
    @Override
    public String getAccessTokenEndpoint() {
        return "https://www.tiktok.com/v" + version + "/auth/authorize";
    }

    @Override
    public String getRefreshTokenEndpoint() {
        throw new UnsupportedOperationException("Facebook doesn't support refreshing tokens");
    }
    
    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://open.tiktokapis.com/v" + version + "/oauth/token";
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return FacebookAccessTokenJsonExtractor.instance();
    }

    @Override
    public ClientAuthentication getClientAuthentication() {
        return RequestBodyAuthenticationScheme.instance();
    }

    @Override
    public TikTokService createService(String apiKey, String apiSecret, String callback, String defaultScope, String responseType, OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig,  HttpClient httpClient) {
        return new TikTokService(this, apiKey, apiSecret, callback, defaultScope, responseType, debugStream, userAgent,httpClientConfig, httpClient);
    }
}
