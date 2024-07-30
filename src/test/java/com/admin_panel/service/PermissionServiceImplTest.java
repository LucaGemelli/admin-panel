package com.admin_panel.service;

import com.admin_panel.dto.PermissionDTO;
import com.admin_panel.dto.SimplePermissionDTO;
import com.admin_panel.exception.ResourceNotFoundException;
import com.admin_panel.model.Permission;
import com.admin_panel.repository.PermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PermissionServiceImplTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPermissions() {
        Permission readPermission = mockReadPermission();
        Permission writePermission = mockWritePermission();
        when(permissionRepository.findAll()).thenReturn(List.of(readPermission, writePermission));
        List<PermissionDTO> permissions = permissionService.getAllPermissions();
        assertEquals(2, permissions.size());
        assertEquals("READ", permissions.get(0).getName());
        assertEquals("WRITE", permissions.get(1).getName());
    }

    @Test
    public void testGetPermissionById() {
        Permission permission = mockReadPermission();
        when(permissionRepository.findById(1L)).thenReturn(Optional.of(permission));
        PermissionDTO permissionDTO = permissionService.getPermissionById(1L);
        assertNotNull(permissionDTO);
        assertEquals("READ", permissionDTO.getName());
    }

    @Test
    public void testCreatePermission() {
        SimplePermissionDTO simplePermissionDTO = new SimplePermissionDTO("READ");
        Permission permission = mockReadPermission();
        when(permissionRepository.save(any(Permission.class))).thenReturn(permission);
        PermissionDTO createdPermission = permissionService.createPermission(simplePermissionDTO);
        assertNotNull(createdPermission);
        assertEquals("READ", createdPermission.getName());
    }

    @Test
    public void testUpdatePermission() {
        Permission existingPermission = mockReadPermission();
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(1L);
        permissionDTO.setName("WRITE");
        when(permissionRepository.findById(1L)).thenReturn(Optional.of(existingPermission));
        when(permissionRepository.save(any(Permission.class))).thenReturn(existingPermission);
        PermissionDTO updatedPermission = permissionService.updatePermission(1L, permissionDTO);
        assertNotNull(updatedPermission);
        assertEquals("WRITE", updatedPermission.getName());
    }

    @Test
    public void testDeletePermission() {
        doNothing().when(permissionRepository).deleteById(1L);
        permissionService.deletePermission(1L);
        verify(permissionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletePermission_PermissionNotFound() {
        Long permissionId = 1L;
        when(permissionRepository.existsById(permissionId)).thenReturn(false);
        ResourceNotFoundException exception =
                assertThrows(ResourceNotFoundException.class, () -> permissionService.deletePermission(permissionId));
        assertEquals("Permission not found with id " + permissionId, exception.getMessage());
        verify(permissionRepository, times(1)).existsById(permissionId);
    }

    private Permission mockReadPermission() {
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setName("READ");
        return permission;

    }

    private Permission mockWritePermission() {
        Permission permission = new Permission();
        permission.setId(2L);
        permission.setName("WRITE");
        return permission;
    }
}
