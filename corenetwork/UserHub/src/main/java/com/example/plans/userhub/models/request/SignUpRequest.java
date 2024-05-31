package com.example.plans.userhub.models.request;

import com.example.plans.userhub.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class SignUpRequest {
    @NotEmpty(message = "Username is mandatory")
    @NotNull(message = "Username is mandatory")
    private String username;

    @NotEmpty(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;

    @NotEmpty(message = "Firstname is mandatory")
    @NotNull(message = "Firstname is mandatory")
    private String firstname;

    @NotEmpty(message = "Lastname is mandatory")
    @NotNull(message = "Lastname is mandatory")
    private String lastname;

    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Phone is mandatory")
    @NotNull(message = "Phone is mandatory")
    private String phone;

    private boolean enabled = true;

    private boolean accountLocked = false;

    private List<Role> roles;

    private List<String> devices;
}
