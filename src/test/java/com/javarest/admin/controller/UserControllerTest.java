package com.javarest.admin.controller;

import com.javarest.admin.controller.converter.ControllerConverter;
import com.javarest.admin.controller.model.UserResponse;
import com.javarest.admin.entity.User;
import com.javarest.admin.service.UserService;
import com.javarest.admin.service.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private ControllerConverter controllerConverter;

    @Test
    public void testCreateUser() {
        UserResponse userResponse = new UserResponse(1L, "firstName", "lastame", "email");
        UserDTO userDTO = new UserDTO(1L, "firstName", "lastame", "email");
        User savedUser = new User();

        when(controllerConverter.convertUserResponseToUserDTO(userResponse)).thenReturn(userDTO);
        when(userService.createUser(userDTO)).thenReturn(savedUser);

        ResponseEntity<User> response = userController.createUser(userResponse);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        UserResponse userResponse = new UserResponse(1L, "firstName", "lastame", "email");
        UserDTO userDTO = new UserDTO(1L, "firstName", "lastame", "email");

        when(userService.getUserById(userId)).thenReturn(userDTO);
        when(controllerConverter.convertUserDTOToUserResponse(userDTO)).thenReturn(userResponse);

        ResponseEntity<UserResponse> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        List<UserDTO> userDTOs = List.of(new UserDTO(1L, "firstName", "lastame", "email"));
        List<UserResponse> userResponses = List.of(new UserResponse(1L, "firstName", "lastame", "email"));

        when(userService.getAllUsers()).thenReturn(userDTOs);
        when(controllerConverter.convertUserDTOToUserResponse(userDTOs.get(0))).thenReturn(userResponses.get(0));

        ResponseEntity<List<UserResponse>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponses, response.getBody());
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        UserResponse userResponse = new UserResponse(1L, "firstName", "lastame", "email");
        UserDTO updatedUser = new UserDTO(1L, "firstName", "lastame", "email");
        UserResponse updatedUserResponse = new UserResponse(1L, "firstName", "lastame", "email");

        when(controllerConverter.convertUserResponseToUserDTO(userResponse)).thenReturn(updatedUser);
        when(userService.updateUser(userId, updatedUser)).thenReturn(updatedUser);
        when(controllerConverter.convertUserDTOToUserResponse(updatedUser)).thenReturn(updatedUserResponse);

        ResponseEntity<UserResponse> response = userController.updateUser(userId, userResponse);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUserResponse, response.getBody());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        ResponseEntity<String> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User successfully deleted!", response.getBody());
    }

}
