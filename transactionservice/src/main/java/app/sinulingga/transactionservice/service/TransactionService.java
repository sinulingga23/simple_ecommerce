package app.sinulingga.transactionservice.service;

import app.sinulingga.transactionservice.dto.AddOrderRequest;
import app.sinulingga.transactionservice.dto.InquiryPaymentRequest;
import app.sinulingga.transactionservice.entity.TransactionResponse;
import app.sinulingga.transactionservice.exception.BadRequestException;
import app.sinulingga.transactionservice.exception.DataNotFoundException;

import java.util.Set;

public interface TransactionService {
    void createOrder(AddOrderRequest request)
            throws BadRequestException, DataNotFoundException;
    void inquiryPayment(InquiryPaymentRequest request)
            throws BadRequestException, DataNotFoundException;
    Set<TransactionResponse> findAll()
            throws BadRequestException, DataNotFoundException;
}
