package app.sinulingga.usersservice.utility;

import app.sinulingga.usersservice.dto.ResponseBasic;
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

}
