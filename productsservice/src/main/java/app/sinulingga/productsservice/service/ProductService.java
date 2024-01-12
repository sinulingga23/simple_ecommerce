package app.sinulingga.productsservice.service;

import app.sinulingga.productsservice.dto.AddProductRequest;
import app.sinulingga.productsservice.dto.ListProductRequest;
import app.sinulingga.productsservice.dto.ProductResponse;
import app.sinulingga.productsservice.dto.ResponsePagination;
import app.sinulingga.productsservice.exception.BadRequestException;
import app.sinulingga.productsservice.exception.DataNotFoundException;

import java.util.Set;

public interface ProductService {
    void add(AddProductRequest request)
            throws BadRequestException, DataNotFoundException;
    ResponsePagination findAll(ListProductRequest request)
        throws BadRequestException, DataNotFoundException;
    void deductQty(String productId, Long qty)
            throws BadRequestException, DataNotFoundException;
}
