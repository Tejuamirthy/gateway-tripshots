package com.tripshots.gateway.model;

import com.tripshots.gateway.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String name;
    private boolean enabled;
    private Set<Role> roles;
}
