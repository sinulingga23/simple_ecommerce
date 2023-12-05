package app.sinulingga.productsservice.dto;

import java.util.List;

public class AddCategoriesRequest {
    private List<AddCategoryRequest> categories;


    public AddCategoriesRequest() {

    }

    public List<AddCategoryRequest> getCategories() {
        return categories;
    }

    public void setCategories(List<AddCategoryRequest> categories) {
        this.categories = categories;
    }


}
