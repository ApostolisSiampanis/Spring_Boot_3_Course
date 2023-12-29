package com.example.demosecurity.service;

import com.example.demosecurity.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto findByUserName(String userName);

    void save(UserDto userDto);
}
