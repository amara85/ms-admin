package com.javarest.admin.service.converter;

import com.javarest.admin.entity.User;
import com.javarest.admin.service.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class ServiceConverter {

    public UserDTO convertUserToUserDTO(User user) {
        return new UserDTO(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }

    public User convertUserDTOToUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.id())
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .email(userDTO.email())
                .build();
    }


}
