package app.sinulingga.productsservice.service;

import app.sinulingga.productsservice.dto.AddCategoriesRequest;
import app.sinulingga.productsservice.dto.AddCategoryRequest;
import app.sinulingga.productsservice.exception.BadRequestException;

import java.util.List;

public interface CategoryService {
    void addCategories(AddCategoriesRequest request)
            throws BadRequestException;
}
