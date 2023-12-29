package com.example.demosecurity.mapper;

import com.example.demosecurity.dto.UserDto;
import com.example.demosecurity.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User mapToUser(UserDto userDto) {
        return User.builder()
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .enabled(true)
                .build();
    }

    @Override
    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }
}
