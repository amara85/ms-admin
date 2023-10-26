package com.javarest.admin.service;

import com.javarest.admin.entity.User;
import com.javarest.admin.repository.UserRepository;
import com.javarest.admin.service.converter.ServiceConverter;
import com.javarest.admin.service.dto.UserDTO;
import com.javarest.admin.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ServiceConverter serviceConverter;


    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO(1L, "firstName", "lastName", "email");
        User user = new User();

        when(serviceConverter.convertUserDTOToUser(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(userDTO);

        verify(serviceConverter).convertUserDTOToUser(userDTO);
        verify(userRepository).save(user);

        assertEquals(user, createdUser);
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        UserDTO userDTO =  new UserDTO(1L, "firstName", "lastName", "email");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(serviceConverter.convertUserToUserDTO(user)).thenReturn(userDTO);

        UserDTO retrievedUser = userService.getUserById(userId);

        verify(userRepository).findById(userId);
        verify(serviceConverter).convertUserToUserDTO(user);

        assertEquals(userDTO, retrievedUser);
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = Arrays.asList(new User(), new User());
        List<UserDTO> userDTOList = Arrays.asList(new UserDTO(1L, "firstName", "lastName", "email"), new UserDTO(2L, "firstName", "lastName", "email"));

        when(userRepository.findAll()).thenReturn(userList);
        when(serviceConverter.convertUserToUserDTO(any(User.class))).thenReturn(userDTOList.get(0), userDTOList.get(1));

        List<UserDTO> retrievedUsers = userService.getAllUsers();

        verify(userRepository).findAll();
        verify(serviceConverter, times(2)).convertUserToUserDTO(any(User.class));

        assertEquals(userDTOList, retrievedUsers);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO(1L, "firstName", "lastName", "email");
        User existingUser = new User();
        User updatedUser = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(serviceConverter.convertUserToUserDTO(updatedUser)).thenReturn(userDTO);
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        UserDTO updatedUserDTO = userService.updateUser(userId, userDTO);

        verify(userRepository).findById(userId);
        verify(serviceConverter).convertUserToUserDTO(updatedUser);
        verify(userRepository).save(existingUser);

        assertEquals(userDTO, updatedUserDTO);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }

}
