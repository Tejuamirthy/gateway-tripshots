package com.tripshots.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String name;
    private boolean enabled;
}
