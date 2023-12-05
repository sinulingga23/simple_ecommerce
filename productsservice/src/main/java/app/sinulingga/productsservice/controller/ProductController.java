package app.sinulingga.productsservice.controller;

import app.sinulingga.productsservice.dto.*;
import app.sinulingga.productsservice.exception.BadRequestException;
import app.sinulingga.productsservice.exception.DataNotFoundException;
import app.sinulingga.productsservice.service.ProductService;
import app.sinulingga.productsservice.utility.ResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/products-service/api/v1")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/products")
    public ResponseEntity<ResponseBasic> add(@RequestBody AddProductRequest request) {
        try {
            productService.add(request);
            return ResponseHelper.createResponse(HttpStatus.OK, "Success");
        } catch (BadRequestException e) {
            return ResponseHelper.createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataNotFoundException e) {
            return ResponseHelper.createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/products")
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue ="10", required = false) Integer sizePerPage,
            @RequestParam(value = "", required = false) String name
    ) {
        try {
            log.info("page: " + page);
            log.info("sizePerPage: " + sizePerPage);
            log.info("name: " + name);

            ResponsePagination responsePagination = productService.findAll(new ListProductRequest(
                    name,
                    page,
                    sizePerPage
            ));

            responsePagination.setStatusCode(HttpStatus.OK.value());
            responsePagination.setMessage("Success");

            return new ResponseEntity<>(responsePagination, HttpStatus.OK);
        } catch (BadRequestException e) {
            return ResponseHelper.createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataNotFoundException e) {
            return ResponseHelper.createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
