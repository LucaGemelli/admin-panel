package com.admin_panel.service;

import com.admin_panel.dto.SimpleUserDTO;
import com.admin_panel.dto.UserDTO;
import com.admin_panel.model.User;
import com.admin_panel.model.Permission;
import com.admin_panel.repository.PermissionRepository;
import com.admin_panel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public UserDTO createUser(SimpleUserDTO userDTO) {
        User user = convertToEntity(userDTO);
        return convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(userDTO.getName());
            existingUser.setLogin(userDTO.getLogin());
            existingUser.setPermissions(userDTO.getPermissions().stream()
                    .map(permissionName -> permissionRepository.findByName(permissionName))
                    .collect(Collectors.toSet()));
            return convertToDTO(userRepository.save(existingUser));
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLogin(user.getLogin());
        userDTO.setPermissions(user.getPermissions().stream().map(Permission::getName).collect(Collectors.toSet()));
        return userDTO;
    }

    private User convertToEntity(SimpleUserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        user.setPermissions(userDTO.getPermissions().stream()
                .map(permissionName -> permissionRepository.findByName(permissionName))
                .collect(Collectors.toSet()));
        return user;
    }
}