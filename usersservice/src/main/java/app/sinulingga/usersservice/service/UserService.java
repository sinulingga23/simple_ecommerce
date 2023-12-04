package app.sinulingga.usersservice.service;

import app.sinulingga.usersservice.dto.AddUserRequest;
import app.sinulingga.usersservice.exception.BadRequestException;

public interface UserService {
    void add(AddUserRequest request)
            throws BadRequestException;
}
