package com.google.code.magja.domain;

/**
 *
 * @author kwilson
 */
public enum OrderStatus {

    New("new"),
    OnHold("holded"),
    Processing("processing"),
    Pending("pending"),
    PendingPayment("pending_payment"),
    Complete("complete"),
    Closed("closed"),
    Fraud("fraud"),
    Canceled("canceled");
    private final String value;

    private OrderStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
