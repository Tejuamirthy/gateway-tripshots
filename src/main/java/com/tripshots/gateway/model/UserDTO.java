package com.tripshots.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String password;
    private String username;
    private String name;
    private String role;

}
