package app.sinulingga.transactionservice.dto;

import java.util.Set;

public class AddOrderRequest {
    private String userId;
    private Set<OrderRequest> orders;

    public AddOrderRequest() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<OrderRequest> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderRequest> orders) {
        this.orders = orders;
    }
}
