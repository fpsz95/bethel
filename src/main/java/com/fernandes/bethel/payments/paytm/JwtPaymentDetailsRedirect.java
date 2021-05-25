package com.fernandes.bethel.payments.paytm;

public class JwtPaymentDetailsRedirect {
    private String email;
    private String mobileNo;
    private String orderId;
    private String txnAmount;
    private String userId;
    private String CHECKSUMHASH;

    public JwtPaymentDetailsRedirect() {
    }

    public JwtPaymentDetailsRedirect(String email,
                                     String mobileNo,
                                     String orderId,
                                     String txnAmount,
                                     String userId,
                                     String CHECKSUMHASH) {
        this.email = email;
        this.mobileNo = mobileNo;
        this.orderId = orderId;
        this.txnAmount = txnAmount;
        this.userId = userId;
        this.CHECKSUMHASH = CHECKSUMHASH;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCHECKSUMHASH() {
        return CHECKSUMHASH;
    }

    public void setCHECKSUMHASH(String CHECKSUMHASH) {
        this.CHECKSUMHASH = CHECKSUMHASH;
    }
}
