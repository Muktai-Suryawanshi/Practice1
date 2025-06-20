package com.example.Practice1.Service;

import com.example.Practice1.DTO.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    List<UserDto> getUsersByRole(String role);
}
