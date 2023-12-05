package app.sinulingga.transactionservice.controller;

import app.sinulingga.transactionservice.dto.AddOrderRequest;
import app.sinulingga.transactionservice.dto.InquiryPaymentRequest;
import app.sinulingga.transactionservice.dto.ResponseBasic;
import app.sinulingga.transactionservice.entity.TransactionResponse;
import app.sinulingga.transactionservice.exception.BadRequestException;
import app.sinulingga.transactionservice.exception.DataNotFoundException;
import app.sinulingga.transactionservice.service.TransactionService;
import app.sinulingga.transactionservice.utility.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/transactions-service/api/v1")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create-order")
    public ResponseEntity<ResponseBasic> createOrder(@RequestBody AddOrderRequest request) {
        try {
            transactionService.createOrder(request);
            return ResponseHelper.createResponse(HttpStatus.OK, "Success");
        } catch (BadRequestException e) {
            return ResponseHelper.createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataNotFoundException e) {
            return ResponseHelper.createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/inquiry-payment")
    public ResponseEntity<ResponseBasic> inquiryPayment(@RequestBody InquiryPaymentRequest request) {
        try {
            transactionService.inquiryPayment(request);
            return ResponseHelper.createResponse(HttpStatus.OK, "Success");
        } catch (BadRequestException e) {
            return ResponseHelper.createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataNotFoundException e) {
            return ResponseHelper.createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<ResponseBasic> findAll() {
        try {
            Set<TransactionResponse> response = transactionService.findAll();
            return ResponseHelper.createResponse(HttpStatus.OK, "Success", response);
        } catch (BadRequestException e) {
            return ResponseHelper.createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataNotFoundException e) {
            return ResponseHelper.createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
