package app.sinulingga.transactionservice.dto;

import java.math.BigDecimal;

public class InquiryPaymentRequest {
    private String orderId;
    private BigDecimal amount;

    public InquiryPaymentRequest() {

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
