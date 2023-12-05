package app.sinulingga.productsservice.service;

import app.sinulingga.productsservice.dto.AddCategoryRequest;
import app.sinulingga.productsservice.exception.BadRequestException;

import java.util.List;

public interface CategoryService {
    void addCategories(List<AddCategoryRequest> request)
            throws BadRequestException;
}
