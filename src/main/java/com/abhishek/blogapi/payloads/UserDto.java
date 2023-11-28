package com.abhishek.blogapi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
// import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    

    private Integer id;
    @NotEmpty
    @Size(min=4,message = "Name should be of atleast 4 characters")
    private String name;
    @Email(message = "Email is invalid")
    private String email; 
    @NotEmpty
    @Size(min=4, max = 12, message = "Password must be within range of 4 & 12")
    private String password;
    @NotEmpty
    private String about;
}
