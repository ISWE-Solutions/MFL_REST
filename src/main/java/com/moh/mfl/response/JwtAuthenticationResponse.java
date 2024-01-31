package com.moh.mfl.response;

public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private String expiry;
    private String expiryIn = "seconds";

    public JwtAuthenticationResponse(String accessToken, String expiry) {
        this.accessToken = accessToken;
        this.expiry = expiry;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * @return the expiry
     */
    public String getExpiry() {
        return expiry;
    }

    /**
     * @param expiry the expiry to set
     */
    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    /**
     * @return the expiryIn
     */
    public String getExpiryIn() {
        return expiryIn;
    }

    /**
     * @param expiryIn the expiryIn to set
     */
    public void setExpiryIn(String expiryIn) {
        this.expiryIn = expiryIn;
    }
}
