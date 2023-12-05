package app.sinulingga.transactionservice.service.impl;

import app.sinulingga.transactionservice.definition.StatusPayment;
import app.sinulingga.transactionservice.dto.AddOrderRequest;
import app.sinulingga.transactionservice.dto.OrderRequest;
import app.sinulingga.transactionservice.entity.Product;
import app.sinulingga.transactionservice.entity.Transaction;
import app.sinulingga.transactionservice.entity.TransactionDetail;
import app.sinulingga.transactionservice.entity.User;
import app.sinulingga.transactionservice.exception.BadRequestException;
import app.sinulingga.transactionservice.exception.DataNotFoundException;
import app.sinulingga.transactionservice.repository.ProductRepository;
import app.sinulingga.transactionservice.repository.TransactionDetailRepository;
import app.sinulingga.transactionservice.repository.TransactionRepository;
import app.sinulingga.transactionservice.repository.UserRepository;
import app.sinulingga.transactionservice.service.TransactionService;
import app.sinulingga.transactionservice.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService  {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void createOrder(AddOrderRequest request) throws BadRequestException, DataNotFoundException {
        try {
            if (Validator.isEmpty(request.getUserId()))
                throw new BadRequestException("User Id Empty");
            if (Validator.isEmpty(request.getOrders()))
                throw new BadRequestException("Products Empty");

            Set<UUID> productIds = new HashSet<>();
            for (OrderRequest orderRequest : request.getOrders()) {
                if (Validator.isEmpty(orderRequest.getProductId()))
                    throw new BadRequestException("Product Id Empty");
                if (Validator.isEmpty(orderRequest.getQtty()))
                    throw new BadRequestException("Qtty Empty");
                if (orderRequest.getQtty() <= 0)
                    throw new BadRequestException("Qtty can't minus or zero.");
                productIds.add(UUID.fromString(orderRequest.getProductId()));
            }

            Optional<User> optionalUser = userRepository.findById(UUID.fromString(request.getUserId()));
            if (optionalUser.isEmpty())
                throw new DataNotFoundException("User Not Found");

            Set<Product> products = productRepository.findByIdIn(productIds);
            if (products == null || (products.size() != productIds.size()))
                throw new DataNotFoundException("Data Product Empty");

            Map<String, Product> mapProduct = new HashMap<>();
            BigDecimal totalPayment = BigDecimal.ZERO;
            for (Product product : products) {
                totalPayment = totalPayment.add(product.getPrice());
                mapProduct.put(product.getId().toString(), product);
            }


            for (OrderRequest orderRequest : request.getOrders()) {
                Product product = mapProduct.get(orderRequest.getProductId());

                if (product.getQtty().compareTo(orderRequest.getQtty()) < 0) {
                    throw new BadRequestException("Insufficient Qtty");
                }

                product.setQtty(product.getQtty()-orderRequest.getQtty());
                productRepository.save(product);
            }


            Transaction transaction = new Transaction();
            UUID transactionId = UUID.randomUUID();
            transaction.setId(transactionId);
            transaction.setTransactionTime(LocalDateTime.now());
            transaction.setCreatedBy("System");
            transaction.setCreatedAt(LocalDateTime.now());
            transaction.setStatusPayment(StatusPayment.NOT_PAID);
            transaction.setTotalPayment(totalPayment);
            transaction.setUser(optionalUser.get());

            transactionRepository.save(transaction);

            Set<TransactionDetail> transactionDetails = new HashSet<>();
            for (Product product : products) {
                TransactionDetail transactionDetail = new TransactionDetail();
                transactionDetail.setId(UUID.randomUUID());
                transactionDetail.setTransaction(transaction);
                transactionDetail.setProduct(product);
                transactionDetails.add(transactionDetail);
            }

            transactionDetailRepository.saveAll(transactionDetails);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Id Invalid");
        } catch (BadRequestException | DataNotFoundException e) {
            throw e;
        }
    }
}
