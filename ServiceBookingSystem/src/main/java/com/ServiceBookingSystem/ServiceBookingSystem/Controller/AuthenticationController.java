package com.ServiceBookingSystem.ServiceBookingSystem.Controller;

import com.ServiceBookingSystem.ServiceBookingSystem.Entity.User;
import com.ServiceBookingSystem.ServiceBookingSystem.Services.Authentication.AuthService;
import com.ServiceBookingSystem.ServiceBookingSystem.Services.Jwt.UserDetailsServiceImpl;
import com.ServiceBookingSystem.ServiceBookingSystem.dto.AuthenticationRequest;
import com.ServiceBookingSystem.ServiceBookingSystem.dto.SignupRequestDTO;
import com.ServiceBookingSystem.ServiceBookingSystem.dto.UserDto;
import com.ServiceBookingSystem.ServiceBookingSystem.repository.UserRepository;
import com.ServiceBookingSystem.ServiceBookingSystem.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authentication";

    @PostMapping("/company/sign-up")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDTO signupRequestDTO){
        if(authService.presentByEmail(signupRequestDTO.getEmail())){
            return new ResponseEntity<>("company already exist" , HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createUSer = authService.signupClient(signupRequestDTO);
        return new ResponseEntity<>(createUSer,HttpStatus.OK);
    }
    @PostMapping("/client/sign-up")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDTO signupRequestDTO){
        if(authService.presentByEmail(signupRequestDTO.getEmail())){
            return new ResponseEntity<>("client already exist" , HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createUSer = authService.signupCompany(signupRequestDTO);
        return new ResponseEntity<>(createUSer,HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public  void CreateAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
                                           HttpServletResponse response) throws JSONException, IOException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
            ));
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Incorrect Username or Password" , e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateTocken(userDetails.getUsername());

        User user= userRepository.findFirstByEmail(authenticationRequest.getUsername());

        response.getWriter().write(new JSONObject()
                .put("userId",user.getId())
                .put("role",user.getRole())
                .toString());

        response.addHeader("Access-Control-Expose-Header", "Autherization");
        response.addHeader("Access-Control-Allow-Header", "Autherization" + "X-PINGOTHER, Origin , X-Request-With, Content-Type, Accept, X-Custome-header");

        response.addHeader(HEADER_STRING,TOKEN_PREFIX + jwt);
    }



}
