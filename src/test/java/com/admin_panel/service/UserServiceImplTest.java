package com.admin_panel.service;

import com.admin_panel.dto.SimpleUserDTO;
import com.admin_panel.dto.UserDTO;
import com.admin_panel.exception.ResourceNotFoundException;
import com.admin_panel.model.Permission;
import com.admin_panel.model.User;
import com.admin_panel.repository.PermissionRepository;
import com.admin_panel.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        User user = mockUser();
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        List<UserDTO> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("John Doe", users.getFirst().getName());
    }

    @Test
    public void testGetUserById() {
        User user = mockUser();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserDTO userDTO = userService.getUserById(1L);
        assertNotNull(userDTO);
        assertEquals("John Doe", userDTO.getName());
        assertEquals("johndoe", userDTO.getLogin());
        assertTrue(userDTO.getPermissions().isEmpty());
    }

    @Test
    public void testCreateUser() {
        SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
        simpleUserDTO.setName("John Doe");
        simpleUserDTO.setLogin("johndoe");
        simpleUserDTO.setPermissions(Set.of("READ", "WRITE"));
        User user = mockUser();
        user.setPermissions(simpleUserDTO.getPermissions().stream()
                .map(Permission::new)
                .collect(Collectors.toSet()));

        when(permissionRepository.findByName(anyString())).thenReturn(new Permission("READ"));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO userDTO = userService.createUser(simpleUserDTO);

        assertNotNull(userDTO);
        assertEquals("John Doe", userDTO.getName());
        assertEquals("johndoe", userDTO.getLogin());
        assertEquals(1L, userDTO.getId());
    }

    @Test
    public void testUpdateUser() {
        User existingUser = mockUser();
        UserDTO userDTO = mockUserDTO();
        userDTO.setPermissions(Set.of("READ"));
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(permissionRepository.findByName("READ")).thenReturn(new Permission("READ"));
        when(userRepository.save(existingUser)).thenReturn(existingUser);
        UserDTO updatedUser = userService.updateUser(1L, userDTO);
        assertNotNull(updatedUser);
        assertEquals("Jane Doe", updatedUser.getName());
        assertEquals("janedoe", updatedUser.getLogin());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
    @Test
    public void testDeleteUser_UserNotFound() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(false);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(userId));
        assertEquals("User not found with id " + userId, exception.getMessage());
        verify(userRepository, times(1)).existsById(userId);
    }


    private UserDTO mockUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Jane Doe");
        userDTO.setLogin("janedoe");
        userDTO.setPermissions(Collections.emptySet());
        return userDTO;
    }

    private User mockUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setLogin("johndoe");
        user.setPermissions(Collections.emptySet());
        return user;
    }
}
