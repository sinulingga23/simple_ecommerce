package app.sinulingga.productsservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "statusCode","message","data",
        "currentPage","totalPage"
})
public class ResponsePagination {
    private int statusCode;
    private String message;
    private Object data;
    private int currentPage;
    private int totalPage;

    public ResponsePagination() {

    }

    public ResponsePagination(int statusCode, String message, Object data, int currentPage, int totalPage) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
