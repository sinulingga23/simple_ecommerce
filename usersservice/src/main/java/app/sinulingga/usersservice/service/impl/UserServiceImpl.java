package app.sinulingga.usersservice.service.impl;

import app.sinulingga.usersservice.dto.AddUserRequest;
import app.sinulingga.usersservice.entity.User;
import app.sinulingga.usersservice.exception.BadRequestException;
import app.sinulingga.usersservice.repository.UserRepository;
import app.sinulingga.usersservice.service.UserService;
import app.sinulingga.usersservice.utility.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void add(AddUserRequest request) throws BadRequestException {
        log.info("Request:  " + request);
        try {
            if (Validator.isEmpty(request.getEmail()))
                throw new BadRequestException("Email Empty");
            if (Validator.isEmpty(request.getFullName()))
                throw new BadRequestException("Full Name Empty");
            if (Validator.isEmpty(request.getPhoneNumber().trim()))
                throw new BadRequestException("Phone Number Empty");
            if (!Validator.isValidEmail(request.getEmail().trim()))
                throw new BadRequestException("Email Invalid.");

            if (userRepository.existsByEmail(request.getEmail()))
                throw new BadRequestException("Email Already Exists");
            if (userRepository.existsByPhoneNumber(request.getPhoneNumber()))
                throw new BadRequestException("Phone Number Already Exists");


            User user = new User();
            user.setId(UUID.randomUUID());
            user.setEmail(request.getEmail().trim());
            user.setPhoneNumber(request.getPhoneNumber().trim());
            user.setFullName(request.getFullName().trim());
            user.setCreatedAt(LocalDateTime.now());
            user.setCreatedBy("System");

            userRepository.save(user);
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            log.info("Unexpected Error: " + e.getMessage());
            e.getStackTrace();
            throw new BadRequestException(e.getClass().getSimpleName());
        }
    }
}
