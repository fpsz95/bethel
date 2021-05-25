package com.fernandes.bethel.payments.paytm.json;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Body {

    private ResultInfo resultInfo;
    private String txnToken;
    private Boolean isPromoCodeValid;
    private Boolean authenticated;

    /**
     * No args constructor for use in serialization
     *
     */
    public Body() {
    }

    /**
     *
     * @param authenticated
     * @param txnToken
     * @param isPromoCodeValid
     * @param resultInfo
     */
    public Body(ResultInfo resultInfo, String txnToken, Boolean isPromoCodeValid, Boolean authenticated) {
        super();
        this.resultInfo = resultInfo;
        this.txnToken = txnToken;
        this.isPromoCodeValid = isPromoCodeValid;
        this.authenticated = authenticated;
    }

    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

    public String getTxnToken() {
        return txnToken;
    }

    public void setTxnToken(String txnToken) {
        this.txnToken = txnToken;
    }

    public Boolean getIsPromoCodeValid() {
        return isPromoCodeValid;
    }

    public void setIsPromoCodeValid(Boolean isPromoCodeValid) {
        this.isPromoCodeValid = isPromoCodeValid;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

}