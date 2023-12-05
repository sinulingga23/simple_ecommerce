package app.sinulingga.productsservice.service.impl;

import app.sinulingga.productsservice.dto.AddCategoriesRequest;
import app.sinulingga.productsservice.dto.AddCategoryRequest;
import app.sinulingga.productsservice.dto.CategoryResponse;
import app.sinulingga.productsservice.entity.Category;
import app.sinulingga.productsservice.exception.BadRequestException;
import app.sinulingga.productsservice.exception.DataNotFoundException;
import app.sinulingga.productsservice.repository.CategoryRepository;
import app.sinulingga.productsservice.service.CategoryService;
import app.sinulingga.productsservice.utility.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addCategories(AddCategoriesRequest request) throws BadRequestException {
        try {
            if (Validator.isEmpty(request.getCategories()))
                throw new BadRequestException("Categories Empty");

            for (AddCategoryRequest item : request.getCategories())
                if (Validator.isEmpty(item) || Validator.isEmpty(item.getName()))
                    throw new BadRequestException("Name Empty");

            List<Category> categories = new ArrayList<>();
            request.getCategories().forEach(item -> {
                Category category = new Category();
                category.setId(UUID.randomUUID());
                category.setName(item.getName().trim());
                category.setCreatedAt(LocalDateTime.now());
                category.setCreatedBy("System");
                categories.add(category);
            });

            categoryRepository.saveAll(categories);
        } catch (BadRequestException e) {
            e.getStackTrace();
            log.info("Exception: "  + e.getMessage());
            throw e;
        } catch (Exception e) {
            e.getStackTrace();
            log.info("Exception: "  + e.getMessage());
            throw new BadRequestException(e.getClass().getSimpleName());
        }
    }

    @Override
    public Set<CategoryResponse> findAll() throws DataNotFoundException {
        List<Category> listCategory = categoryRepository.findAll();
        if (listCategory.isEmpty())
            throw new DataNotFoundException("Data Not Found");

        Set<CategoryResponse> categories = new HashSet<>();
        for (Category category : listCategory) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(category.getId().toString());
            categoryResponse.setName(category.getName());
            categories.add(categoryResponse);
        }
        return categories;
    }
}
