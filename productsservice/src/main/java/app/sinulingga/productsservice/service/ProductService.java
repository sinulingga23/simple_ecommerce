package app.sinulingga.productsservice.service;

import app.sinulingga.productsservice.dto.AddProductRequest;
import app.sinulingga.productsservice.exception.BadRequestException;
import app.sinulingga.productsservice.exception.DataNotFoundException;

public interface ProductService {
    void add(AddProductRequest request)
            throws BadRequestException, DataNotFoundException;
}
