package app.sinulingga.productsservice.controller;

import app.sinulingga.productsservice.dto.AddCategoriesRequest;
import app.sinulingga.productsservice.dto.AddCategoryRequest;
import app.sinulingga.productsservice.dto.CategoryResponse;
import app.sinulingga.productsservice.dto.ResponseBasic;
import app.sinulingga.productsservice.exception.BadRequestException;
import app.sinulingga.productsservice.exception.DataNotFoundException;
import app.sinulingga.productsservice.service.CategoryService;
import app.sinulingga.productsservice.utility.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @GetMapping(value = "/categories")
    public ResponseEntity<ResponseBasic> getAll() {
        try {
            Set<CategoryResponse> categories = categoryService.findAll();
            return ResponseHelper.createResponse(HttpStatus.OK, "Success", categories);
        } catch (DataNotFoundException e) {
            return ResponseHelper.createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
