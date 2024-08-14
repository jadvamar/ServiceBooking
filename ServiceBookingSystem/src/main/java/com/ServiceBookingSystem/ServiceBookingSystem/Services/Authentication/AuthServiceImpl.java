package com.ServiceBookingSystem.ServiceBookingSystem.Services.Authentication;

import com.ServiceBookingSystem.ServiceBookingSystem.Entity.User;
import com.ServiceBookingSystem.ServiceBookingSystem.Enums.UserRole;
import com.ServiceBookingSystem.ServiceBookingSystem.dto.SignupRequestDTO;
import com.ServiceBookingSystem.ServiceBookingSystem.dto.UserDto;
import com.ServiceBookingSystem.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient(SignupRequestDTO signupRequestDTO){
        User user = new User ();

        user.setName(signupRequestDTO.getName());
        user.setLastName(signupRequestDTO.getLastName());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(signupRequestDTO.getPassword());
        user.setRole(UserRole.CLIENT);
        return userRepository.save(user).getDto();
    }

    public Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }
    public UserDto signupCompany(SignupRequestDTO signupRequestDTO){
        User user = new User ();

        user.setName(signupRequestDTO.getName());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(signupRequestDTO.getPassword());
        user.setRole(UserRole.COMPANY);
        return userRepository.save(user).getDto();
    }
}
