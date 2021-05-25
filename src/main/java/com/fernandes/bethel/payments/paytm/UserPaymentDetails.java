package com.fernandes.bethel.payments.paytm;

public class UserPaymentDetails {
    private Long userId;
    private String transactionAmount;
    private String orderId;

    public UserPaymentDetails() {
    }

    public UserPaymentDetails(Long userId,
                              String transactionAmount,
                              String orderId) {
        this.userId = userId;
        this.transactionAmount = transactionAmount;
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
