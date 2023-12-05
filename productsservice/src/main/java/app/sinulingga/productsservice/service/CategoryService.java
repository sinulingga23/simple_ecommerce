package app.sinulingga.productsservice.service;

import app.sinulingga.productsservice.dto.AddCategoriesRequest;
import app.sinulingga.productsservice.dto.AddCategoryRequest;
import app.sinulingga.productsservice.dto.CategoryResponse;
import app.sinulingga.productsservice.exception.BadRequestException;
import app.sinulingga.productsservice.exception.DataNotFoundException;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void addCategories(AddCategoriesRequest request)
            throws BadRequestException;
    Set<CategoryResponse> findAll()
            throws DataNotFoundException;
}
