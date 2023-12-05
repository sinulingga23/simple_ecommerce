package app.sinulingga.productsservice.dto;

import java.util.Set;

public class ListProductRequest {
    private String name;
    private Integer page;
    private Integer sizePerPage;

    public ListProductRequest() {

    }

    public ListProductRequest(String name, Integer page, Integer sizePerPage) {
        this.name = name;
        this.page = page;
        this.sizePerPage = sizePerPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSizePerPage() {
        return sizePerPage;
    }

    public void setSizePerPage(Integer sizePerPage) {
        this.sizePerPage = sizePerPage;
    }
}
