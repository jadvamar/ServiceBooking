package com.ServiceBookingSystem.ServiceBookingSystem.Controller;

import com.ServiceBookingSystem.ServiceBookingSystem.Services.Authentication.AuthService;
import com.ServiceBookingSystem.ServiceBookingSystem.dto.SignupRequestDTO;
import com.ServiceBookingSystem.ServiceBookingSystem.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;
    @PostMapping("/company/signu-up")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDTO signupRequestDTO){
        if(authService.presentByEmail(signupRequestDTO.getEmail())){
            return new ResponseEntity<>("company already exist" , HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createUSer = authService.signupClient(signupRequestDTO);
        return new ResponseEntity<>(createUSer,HttpStatus.OK);
    }
    @PostMapping("/client/signu-up")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDTO signupRequestDTO){
        if(authService.presentByEmail(signupRequestDTO.getEmail())){
            return new ResponseEntity<>("client already exist" , HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createUSer = authService.signupClient(signupRequestDTO);
        return new ResponseEntity<>(createUSer,HttpStatus.OK);
    }
}
