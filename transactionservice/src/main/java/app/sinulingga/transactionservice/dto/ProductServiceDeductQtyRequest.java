package app.sinulingga.transactionservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductServiceDeductQtyRequest {
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("qty")
    private Long qty;

    public ProductServiceDeductQtyRequest() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
}
