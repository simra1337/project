package com.spring.ecommerce.dtos;

import com.spring.ecommerce.entities.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterCustomerDto {

    @Email(message = "should be a well-formed email")
    private String email;
    @Size(min = 3, message = "should contain at least {min} characters")
    private String firstName;
    private String middleName;
    @Size(min = 3, message = "should contain at least {min} characters")
    private String lastName;
    @NotNull
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15})"
            ,message="Password must contain 8-15 Characters with atleast 1 Lower case, " +
            "1 Upper case, 1 Special Character, 1 Number")
    private String password;
    @Size(min = 10, max = 10, message = "should contain at least {min} digits only")
    private String contact;
    private Address address;
    private String confirmPassword;
}
