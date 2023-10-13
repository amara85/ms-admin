package com.javarest.admin.service;

import com.javarest.admin.entity.User;
import com.javarest.admin.service.dto.UserDTO;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);

    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long userId);
}
