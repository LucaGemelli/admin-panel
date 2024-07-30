package com.admin_panel.service;

import com.admin_panel.dto.PermissionDTO;
import com.admin_panel.dto.SimplePermissionDTO;
import com.admin_panel.exception.ResourceNotFoundException;
import com.admin_panel.model.Permission;
import com.admin_panel.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PermissionDTO getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public PermissionDTO createPermission(SimplePermissionDTO permissionDTO) {
        Permission permission = convertToEntity(permissionDTO);
        return convertToDTO(permissionRepository.save(permission));
    }

    @Override
    public PermissionDTO updatePermission(Long id, PermissionDTO permissionDTO) {
        Permission existingPermission = permissionRepository.findById(id).orElse(null);
        if (existingPermission != null) {
            existingPermission.setName(permissionDTO.getName());
            return convertToDTO(permissionRepository.save(existingPermission));
        }
        return null;
    }

    @Override
    public void deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Permission not found with id " + id);
        }
        permissionRepository.deleteById(id);
    }

    private PermissionDTO convertToDTO(Permission permission) {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(permission.getId());
        permissionDTO.setName(permission.getName());
        return permissionDTO;
    }

    private Permission convertToEntity(SimplePermissionDTO permissionDTO) {
        Permission permission = new Permission();
        permission.setName(permissionDTO.getName());
        return permission;
    }
}