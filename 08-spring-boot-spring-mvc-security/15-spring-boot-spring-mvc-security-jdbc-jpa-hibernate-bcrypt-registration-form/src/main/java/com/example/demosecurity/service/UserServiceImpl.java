package com.example.demosecurity.service;

import com.example.demosecurity.dto.UserDto;
import com.example.demosecurity.entity.Role;
import com.example.demosecurity.entity.User;
import com.example.demosecurity.entity.UserRoles;
import com.example.demosecurity.mapper.UserMapper;
import com.example.demosecurity.repository.UserRepository;
import com.example.demosecurity.repository.UserRolesDao;
import com.example.demosecurity.security.DemoSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRolesDao userRolesDao;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRolesDao userRolesDao, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userRolesDao = userRolesDao;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDto findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            return null;
        }
        return userMapper.mapToUserDto(user);
    }

    @Override
    public void save(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        user.setPassword(DemoSecurityConfig.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);

        // Just for implicit demo purposes, we add EMPLOYEE role to the user.
        UserRoles userRoles = new UserRoles(user.getId(), 1L);
        userRolesDao.save(userRoles);
    }
}
