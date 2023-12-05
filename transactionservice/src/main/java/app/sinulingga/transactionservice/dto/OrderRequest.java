package app.sinulingga.transactionservice.dto;

import org.apache.commons.logging.Log;

public class OrderRequest {
    private String productId;
    private Long qtty;

    public OrderRequest() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getQtty() {
        return qtty;
    }

    public void setQtty(Long qtty) {
        this.qtty = qtty;
    }
}
