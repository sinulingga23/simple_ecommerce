package app.sinulingga.usersservice.service;

import app.sinulingga.usersservice.dto.AddUserRequest;
import app.sinulingga.usersservice.dto.UserResponse;
import app.sinulingga.usersservice.exception.BadRequestException;
import app.sinulingga.usersservice.exception.DataNotFoundException;

import java.util.Set;

public interface UserService {
    void add(AddUserRequest request)
            throws BadRequestException;
    Set<UserResponse> findAll()
            throws DataNotFoundException;
}
