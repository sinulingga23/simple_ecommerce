package app.sinulingga.productsservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeductQtyRequest {

    @JsonProperty("productId")
    private String productId;
    @JsonProperty("qty")
    private Long qty;

    public DeductQtyRequest() {

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
