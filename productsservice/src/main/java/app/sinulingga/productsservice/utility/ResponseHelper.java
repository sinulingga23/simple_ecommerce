package app.sinulingga.productsservice.utility;

import app.sinulingga.productsservice.dto.ResponseBasic;
import app.sinulingga.productsservice.dto.ResponsePagination;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    public static ResponseBasic makeResponseBasic(int statusCode, String message) {
        return new ResponseBasic(statusCode, message, null);
    }

    public static ResponseBasic makeResponseBasic(int statusCode, String message, Object data) {
        return new ResponseBasic(statusCode, message, data);
    }


    public static ResponseEntity<ResponseBasic> createResponse(HttpStatus status, String message, Object data) {
        ResponseBasic response = makeResponseBasic(status.value(), message, data);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<ResponseBasic> createResponse(HttpStatus status, String message) {
        ResponseBasic response = makeResponseBasic(status.value(), message, null);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<ResponsePagination> createResponsePagination(
            HttpStatus status, String message, Object data, int currentPage, int totalPage
    ) {
        ResponsePagination response = new ResponsePagination(
                status.value(),
                message,
                data,
                currentPage,
                totalPage
        );
        return new ResponseEntity<>(response, status);
    }
}
