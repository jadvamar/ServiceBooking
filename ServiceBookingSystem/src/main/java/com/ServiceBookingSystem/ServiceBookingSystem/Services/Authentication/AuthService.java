package com.ServiceBookingSystem.ServiceBookingSystem.Services.Authentication;

import com.ServiceBookingSystem.ServiceBookingSystem.dto.SignupRequestDTO;
import com.ServiceBookingSystem.ServiceBookingSystem.dto.UserDto;

public interface AuthService {
    UserDto signupClient(SignupRequestDTO signupRequestDTO);
    Boolean presentByEmail(String email);
    UserDto signupCompany(SignupRequestDTO signupRequestDTO);
}
