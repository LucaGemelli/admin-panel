package com.admin_panel.service;

import com.admin_panel.dto.PermissionDTO;
import com.admin_panel.dto.SimplePermissionDTO;

import java.util.List;

public interface PermissionService {
    List<PermissionDTO> getAllPermissions();
    PermissionDTO getPermissionById(Long id);
    PermissionDTO createPermission(SimplePermissionDTO permissionDTO);
    PermissionDTO updatePermission(Long id, PermissionDTO permissionDTO);
    void deletePermission(Long id);
}
