package com.fernandes.bethel.payments.paytm.json;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Head {

    private String responseTimestamp;
    private String version;
    private String signature;

    /**
     * No args constructor for use in serialization
     *
     */
    public Head() {
    }

    /**
     *
     * @param responseTimestamp
     * @param signature
     * @param version
     */
    public Head(String responseTimestamp, String version, String signature) {
        super();
        this.responseTimestamp = responseTimestamp;
        this.version = version;
        this.signature = signature;
    }

    public String getResponseTimestamp() {
        return responseTimestamp;
    }

    public void setResponseTimestamp(String responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}