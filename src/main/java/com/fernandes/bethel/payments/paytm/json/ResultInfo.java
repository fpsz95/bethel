package com.fernandes.bethel.payments.paytm.json;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ResultInfo {

    private String resultStatus;
    private String resultCode;
    private String resultMsg;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResultInfo() {
    }

    /**
     *
     * @param resultStatus
     * @param resultCode
     * @param resultMsg
     */
    public ResultInfo(String resultStatus, String resultCode, String resultMsg) {
        super();
        this.resultStatus = resultStatus;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}