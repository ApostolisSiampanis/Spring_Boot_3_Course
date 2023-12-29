package com.example.demosecurity.mapper;

import com.example.demosecurity.dto.UserDto;
import com.example.demosecurity.entity.User;

public interface UserMapper {
    User mapToUser(UserDto userDto);

    UserDto mapToUserDto(User user);
}
