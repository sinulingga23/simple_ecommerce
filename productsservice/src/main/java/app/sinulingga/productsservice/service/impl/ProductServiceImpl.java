package app.sinulingga.productsservice.service.impl;

import app.sinulingga.productsservice.dto.AddProductRequest;
import app.sinulingga.productsservice.dto.ListProductRequest;
import app.sinulingga.productsservice.dto.ProductResponse;
import app.sinulingga.productsservice.dto.ResponsePagination;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
            if (Validator.isEmpty(request.getPrice()))
                throw new BadRequestException("Prince can't empty");
            if (request.getQtty() <= 0)
                throw new BadRequestException("Qtty can't zero or minus.");
            if (request.getPrice().signum() == 0 || request.getPrice().signum() == -1)
                throw new BadRequestException("Price can't zero or minus.");

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
            product.setPrice(request.getPrice());
            product.setCreatedAt(LocalDateTime.now());
            product.setCreatedBy("System");

            productRepository.save(product);
        } catch (IllegalArgumentException e) {
            e.getStackTrace();
            log.info("Exception: " + e.getMessage());
            throw new BadRequestException("Id Invalid.");
        }  catch (BadRequestException | DataNotFoundException e) {
            e.getStackTrace();
            log.info("Exception: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            e.getStackTrace();
            log.info("Exception: " + e.getMessage());
            throw new BadRequestException(e.getClass().getSimpleName());
        }
    }

    @Override
    public ResponsePagination findAll(ListProductRequest request)
            throws BadRequestException, DataNotFoundException {
        if (request.getSizePerPage() == null || request.getSizePerPage() > 100 || request.getSizePerPage() <= 0)
            throw new BadRequestException("Size Per Page can't greater than 100 lower/same than 0");
        if (request.getPage() == null || request.getPage() < 0)
            throw new BadRequestException("Page can't lower than 0");
        if (request.getName() == null)
            request.setName("");

        Pageable pageable = PageRequest.of(request.getPage(), request.getSizePerPage());
        Page<Product> listPageProduct = productRepository.findByNameContaining(request.getName().toLowerCase(), pageable);
        if (listPageProduct.isEmpty())
            throw new DataNotFoundException("Data Not Found");

        Set<ProductResponse> products = new HashSet<>();
        listPageProduct.forEach(item -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(item.getId().toString());
            productResponse.setName(item.getName());
            productResponse.setQtty(item.getQtty());
            productResponse.setDescription(item.getDescription());
            productResponse.setPrice(item.getPrice());

            if (item.getCategories() != null && !item.getCategories().isEmpty()) {

                Set<String> categories = new HashSet<>();

                item.getCategories().forEach(itemCategory -> {
                    categories.add(itemCategory.getId().toString());
                });

                productResponse.setCategories(categories);
            }

            products.add(productResponse);
        });

        ResponsePagination responsePagination = new ResponsePagination();
        responsePagination.setData(products);
        responsePagination.setCurrentPage(request.getPage());
        responsePagination.setTotalPage(listPageProduct.getTotalPages());

        return responsePagination;
    }
}
