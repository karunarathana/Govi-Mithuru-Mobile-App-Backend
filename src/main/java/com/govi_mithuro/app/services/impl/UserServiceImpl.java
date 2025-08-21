package com.govi_mithuro.app.services.impl;


import com.govi_mithuro.app.dto.LoginDto;
import com.govi_mithuro.app.dto.UserDto;
import com.govi_mithuro.app.model.UserEntity;
import com.govi_mithuro.app.repo.UserRepo;
import com.govi_mithuro.app.response.login.LoginResponse;
import com.govi_mithuro.app.response.user.CreateUserResponse;
import com.govi_mithuro.app.services.JwtService;
import com.govi_mithuro.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    @CachePut(value = "users",key = "#userDto.userEmail")
    @Override
    public CreateUserResponse createUser(UserDto userDto) {
        logger.info("Method Executing Starting In createUser | userDTO={}", userDto);
        CreateUserResponse response = new CreateUserResponse();
        if (checkExistingUserInTheDatabase(userDto.getUserEmail())) {
            logger.info("Existing User | userDTO={}", userDto);
            response.setResponseMessage("Existing User");
            response.setResponseCode("450");
            return response;
        }

        UserEntity userEntity = assignValueUserEntity(userDto);
        UserEntity savedUser = userRepo.save(userEntity);

        if (String.valueOf(savedUser.getUserId()) != null) {
            logger.info("User saved successfully | userId={}", savedUser.getUserId());
            response.setResponseMessage("User saved successfully");
            response.setResponseCode("200");
        } else {
            logger.warn("User save failed for DTO={}", userDto);
            response.setResponseMessage("User save failed");
            response.setResponseCode("400");
        }

        return response;
    }

    private boolean checkExistingUserInTheDatabase(String userEmail) {
        logger.info("Method Executing Starting In checkExistingUserInTheDatabase |userEmail={}", userEmail);
        Optional<UserEntity> byUserEmail = userRepo.findByUserEmail(userEmail);
        return byUserEmail.isPresent();
    }

    private UserEntity assignValueUserEntity(UserDto userDto) {
        logger.info("Method Executing Starting In assignValueUserEntity |userDto={}", userDto);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDto.getUserName());
        userEntity.setEmail(userDto.getUserEmail());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRole(userDto.getRole());
        logger.info("Method Executing Completed In assignValueUserEntity |userEntity={}", userEntity);
        return userEntity;
    }

    public LoginResponse validateUser(LoginDto loginDto) {
        // Password = "Maheesha123"
        logger.info("Method Executing Starting In validateUser");
        LoginResponse response = new LoginResponse();
        Optional<UserEntity> byUserEmail = userRepo.findByUserEmail(loginDto.getUserEmail());
        if (byUserEmail.isPresent()) {
            if (byUserEmail.get().getEmail().equals(loginDto.getUserEmail())) {
                if (passwordEncoder.matches(loginDto.getPassword(), byUserEmail.get().getPassword())) {
                    String newAccessToken = jwtService.generateAccessToken(byUserEmail.get().getUserName());
                    String newRefreshToken = jwtService.generateRefreshToken(byUserEmail.get().getUserName());

                    response.setMessage("User credential is correct");
                    response.setNewAccessToken(newAccessToken);
                    response.setNewRefreshToken(newRefreshToken);
                    response.setUsername(byUserEmail.get().getUserName());
                    return response;
                } else {
                    response.setMessage("User password is incorrect: ");
                    return response;
                }
            }
            return null;
        } else {
            logger.warn("User not found with userEmail: {}", loginDto.getUserEmail());
            response.setMessage("User not found with userEmail");
            return response;
        }
    }

    public String forgotPassword(String userEmail, String newPassword) {
        Optional<UserEntity> byUserEmail = userRepo.findByUserEmail(userEmail);
        if (byUserEmail.isPresent()) {
            userRepo.updateUserPassword(userEmail, passwordEncoder.encode(newPassword));
            return "Forgot Password Successfully";
        }
        return ("User not found with username: " + userEmail);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> allUsers = userRepo.findAll();
        return allUsers;
    }

    @Override
    public String deleteUser(int userId) {
        logger.info("Method Executing Starting In deleteUser | userId={}", userId);
        userRepo.deleteById(userId);
        logger.info("Method Executing Completed In deleteUser | userId={}", userId);
        return "User Deleted Successfully";
    }

    @Cacheable(value = "user",key = "#userId")
    private UserEntity getUserByUserId(LoginDto loginDto){
        logger.info("Method Executing Starting In getUserByUserId |UserEmail={}",loginDto.getUserEmail());
        Optional<UserEntity> byUserEmail = userRepo.findByUserEmail(loginDto.getUserEmail());
        logger.info("Method Executing Completed In getUserByUserId |UserDetails={}",byUserEmail);
        return byUserEmail.get();
    }
}
