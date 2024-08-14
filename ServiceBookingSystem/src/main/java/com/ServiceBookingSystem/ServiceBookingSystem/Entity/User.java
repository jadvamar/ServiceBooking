package com.ServiceBookingSystem.ServiceBookingSystem.Entity;

import com.ServiceBookingSystem.ServiceBookingSystem.Enums.UserRole;
import com.ServiceBookingSystem.ServiceBookingSystem.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.query.criteria.JpaSubQuery;

@Entity
@Table(name="Users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String email;

    private String password;

    private  String name;

    private String lastName;

    private String phone;

    private UserRole role;

    public UserDto getDto(){
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setRole(role);
        return userDto;
    }
}
