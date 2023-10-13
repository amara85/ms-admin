package com.javarest.admin.controller;

import com.javarest.admin.controller.converter.ControllerConverter;
import com.javarest.admin.controller.model.UserResponse;
import com.javarest.admin.entity.User;
import com.javarest.admin.service.UserService;
import com.javarest.admin.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    private ControllerConverter controllerConverter;

    // create User REST API
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserResponse userResponse){
        User savedUser = userService.createUser(controllerConverter.convertUserResponseToUserDTO(userResponse));
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId){
        UserDTO userDTO = userService.getUserById(userId);
        return new ResponseEntity<>(controllerConverter.convertUserDTOToUserResponse(userDTO), HttpStatus.OK);
    }

    // Get All Users REST API
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserDTO> userDTOs = userService.getAllUsers();
        return new ResponseEntity<>(userDTOs.stream().map(controllerConverter::convertUserDTOToUserResponse).collect(Collectors.toList()), HttpStatus.OK);
    }

    //Update User REST API
    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody UserResponse userResponse){
        UserDTO updatedUser = userService.updateUser(userId, controllerConverter.convertUserResponseToUserDTO(userResponse));
        return new ResponseEntity<>(controllerConverter.convertUserDTOToUserResponse(updatedUser), HttpStatus.OK);
    }

    //Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
