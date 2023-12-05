package app.sinulingga.usersservice.controller;

import app.sinulingga.usersservice.dto.AddUserRequest;
import app.sinulingga.usersservice.dto.ResponseBasic;
import app.sinulingga.usersservice.dto.UserResponse;
import app.sinulingga.usersservice.exception.BadRequestException;
import app.sinulingga.usersservice.exception.DataNotFoundException;
import app.sinulingga.usersservice.service.UserService;
import app.sinulingga.usersservice.utility.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/users-service/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    public ResponseEntity<ResponseBasic> add(@RequestBody AddUserRequest request) {
        try {
            userService.add(request);
            return ResponseHelper.createResponse(HttpStatus.OK, "Success");
        } catch (BadRequestException e) {
            return ResponseHelper.createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseBasic> findAll() {
        try {
            Set<UserResponse> users = userService.findAll();
            return ResponseHelper.createResponse(HttpStatus.OK, "Success", users);
        } catch (DataNotFoundException e) {
            return ResponseHelper.createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
