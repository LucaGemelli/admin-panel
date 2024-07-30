package com.admin_panel.service;

import com.admin_panel.dto.PermissionDTO;

import java.util.List;

public interface PermissionService {
    List<PermissionDTO> getAllPermissions();
    PermissionDTO getPermissionById(Long id);
    PermissionDTO createPermission(PermissionDTO permissionDTO);
    PermissionDTO updatePermission(Long id, PermissionDTO permissionDTO);
    void deletePermission(Long id);
}
