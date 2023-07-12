package com.admarv.saas.fb.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class AccessToken {
    
    private String accessToken;
    private String tokenType;
    private Integer expiresIn;

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "RespAccessToken [accessToken=" + accessToken + ", tokenType=" + tokenType + ", expiresIn=" + expiresIn
                + "]";
    }

}
