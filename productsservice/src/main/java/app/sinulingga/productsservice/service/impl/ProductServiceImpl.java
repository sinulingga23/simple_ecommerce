package app.sinulingga.productsservice.service.impl;

import app.sinulingga.productsservice.dto.AddProductRequest;
import app.sinulingga.productsservice.entity.Category;
import app.sinulingga.productsservice.entity.Product;
import app.sinulingga.productsservice.exception.BadRequestException;
import app.sinulingga.productsservice.exception.DataNotFoundException;
import app.sinulingga.productsservice.repository.CategoryRepository;
import app.sinulingga.productsservice.repository.ProductRepository;
import app.sinulingga.productsservice.service.ProductService;
import app.sinulingga.productsservice.utility.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService  {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void add(AddProductRequest request) throws BadRequestException, DataNotFoundException {
        try {
            if (Validator.isEmpty(request.getName()))
                throw new BadRequestException("Nam Empty");
            if (Validator.isEmpty(request.getDescription()))
                throw new BadRequestException("Description Empty");
            if (Validator.isEmpty(request.getQtty()))
                throw new BadRequestException("Qtty Empty");
            if (Validator.isEmpty(request.getCategories()))
                throw new BadRequestException("Categories Empty");

            Set<UUID> listCategoryId = new HashSet<>();
            request.getCategories().forEach(item -> {
                listCategoryId.add(UUID.fromString(item));
            });

            Set<Category> categories = categoryRepository.findAllByIdIn(listCategoryId);
            if (categories.size() != listCategoryId.size())
                throw new DataNotFoundException("Category Not Found");

            Product product = new Product();
            product.setId(UUID.randomUUID());
            product.setCategories(categories);
            product.setName(request.getName().trim());
            product.setDescription(request.getDescription().trim());
            product.setQtty(request.getQtty());
            product.setCreatedAt(LocalDateTime.now());
            product.setCreatedBy("System");

            productRepository.save(product);
        } catch (BadRequestException | DataNotFoundException e) {
            e.getStackTrace();
            log.info("Exception: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            e.getStackTrace();
            log.info("Exception: " + e.getMessage());
            throw new BadRequestException(e.getClass().getSimpleName());
        }
    }
}
