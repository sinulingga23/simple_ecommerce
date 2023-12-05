package app.sinulingga.productsservice.dto;

public class AddCategoryRequest {
    private String name;

    public AddCategoryRequest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AddCategoryRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
