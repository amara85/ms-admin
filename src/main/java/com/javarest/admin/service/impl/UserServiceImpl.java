package com.javarest.admin.service.impl;

import com.javarest.admin.entity.User;
import com.javarest.admin.service.converter.ServiceConverter;
import com.javarest.admin.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import com.javarest.admin.repository.UserRepository;
import com.javarest.admin.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ServiceConverter serviceConverter;

    @Override
    public User createUser(UserDTO userDTO) {
        return userRepository.save(serviceConverter.convertUserDTOToUser(userDTO));
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return serviceConverter.convertUserToUserDTO(optionalUser.get());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(serviceConverter::convertUserToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).get();
        existingUser.setFirstName(userDTO.firstName());
        existingUser.setLastName(userDTO.lastName());
        existingUser.setEmail(userDTO.email());
        User updatedUser = userRepository.save(existingUser);
        return serviceConverter.convertUserToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
