package com.ServiceBookingSystem.ServiceBookingSystem.dto;

import com.ServiceBookingSystem.ServiceBookingSystem.Enums.UserRole;
import lombok.Data;

@Data
public class SignupRequestDTO {
    private Long id;

    private String email;

    private String password;

    private  String name;

    private String lastName;

    private String phone;

}
