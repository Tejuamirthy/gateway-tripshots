package com.tripshots.gateway.service;

import com.tripshots.gateway.entity.Role;
import com.tripshots.gateway.entity.User;
import com.tripshots.gateway.model.UserDTO;
import com.tripshots.gateway.repository.RoleRepository;
import com.tripshots.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    public void saveUser(UserDTO userin) {
        User user = new User();
        user.setEmail(userin.getEmail());
        user.setName(userin.getName());
        user.setUsername(userin.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userin.getPassword()));
        Role userRole = roleRepository.findByRole(userin.getRole());
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

}
