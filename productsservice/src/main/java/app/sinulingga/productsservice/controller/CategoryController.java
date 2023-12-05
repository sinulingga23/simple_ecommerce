package app.sinulingga.productsservice.controller;

import app.sinulingga.productsservice.dto.AddCategoriesRequest;
import app.sinulingga.productsservice.dto.AddCategoryRequest;
import app.sinulingga.productsservice.dto.ResponseBasic;
import app.sinulingga.productsservice.exception.BadRequestException;
import app.sinulingga.productsservice.service.CategoryService;
import app.sinulingga.productsservice.utility.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products-service/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/category-bulk-upload")
    public ResponseEntity<ResponseBasic> categoryBulkUpload(@RequestBody AddCategoriesRequest request) {
        try {
            categoryService.addCategories(request);
            return ResponseHelper.createResponse(HttpStatus.OK, "Success");
        } catch (BadRequestException e) {
            return ResponseHelper.createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
