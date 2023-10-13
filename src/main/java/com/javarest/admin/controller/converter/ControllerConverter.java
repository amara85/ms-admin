package com.javarest.admin.controller.converter;

import com.javarest.admin.controller.model.UserResponse;
import com.javarest.admin.service.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class ControllerConverter {

    public UserDTO convertUserResponseToUserDTO(UserResponse userResponse) {
        return new UserDTO(userResponse.id(),
                userResponse.firstName(),
                userResponse.lastName(),
                userResponse.email());
    }

    public UserResponse convertUserDTOToUserResponse(UserDTO userDTO) {
        return new UserResponse(userDTO.id(),
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.email());
    }

}
