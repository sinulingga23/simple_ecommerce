package app.sinulingga.productsservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.Set;

@JsonPropertyOrder({
        "id", "name", "description",
        "qtty","price", "categories"
})
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private Long qtty;
    private BigDecimal price;
    private Set<String> categories;

    public ProductResponse() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQtty() {
        return qtty;
    }

    public void setQtty(Long qtty) {
        this.qtty = qtty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", qtty=" + qtty +
                ", price=" + price +
                '}';
    }
}
