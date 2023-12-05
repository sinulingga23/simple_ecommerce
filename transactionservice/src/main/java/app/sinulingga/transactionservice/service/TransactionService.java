package app.sinulingga.transactionservice.service;

import app.sinulingga.transactionservice.dto.AddOrderRequest;
import app.sinulingga.transactionservice.exception.BadRequestException;
import app.sinulingga.transactionservice.exception.DataNotFoundException;

public interface TransactionService {
    void createOrder(AddOrderRequest request)
            throws BadRequestException, DataNotFoundException;
}
