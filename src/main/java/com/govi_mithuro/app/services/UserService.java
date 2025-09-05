package com.govi_mithuro.app.services;


import com.govi_mithuro.app.dto.LoginDto;
import com.govi_mithuro.app.dto.UserDto;
import com.govi_mithuro.app.model.UserEntity;
import com.govi_mithuro.app.response.login.LoginResponse;
import com.govi_mithuro.app.response.user.CreateUserResponse;

import java.util.List;

public interface UserService {
    CreateUserResponse createUser(UserDto userDto);
    LoginResponse validateUser(LoginDto loginDto);
    String forgotPassword(String userEmail,String newPassword);
    List<UserEntity> getAllUsers();
    String deleteUser(int userId);
    String getUserOTPCode(String email,String otpCode);
    String sendOtpCodeToUserEmail(String email);

}
