package com.tripshots.gateway.service;

import com.tripshots.gateway.entity.Role;
import com.tripshots.gateway.entity.User;
import com.tripshots.gateway.model.UserDTO;
import com.tripshots.gateway.repository.RoleRepository;
import com.tripshots.gateway.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void saveUser(UserDTO userin) {
        User user = new User();
        BeanUtils.copyProperties(userin, user);
        user.setPassword(bCryptPasswordEncoder.encode(userin.getPassword()));
        userRepository.save(user);
    }

}
