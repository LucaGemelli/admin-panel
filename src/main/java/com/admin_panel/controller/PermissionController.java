package com.admin_panel.controller;

import com.admin_panel.dto.SimplePermissionDTO;
import com.admin_panel.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.admin_panel.dto.PermissionDTO;

@RestController
@RequestMapping("/permissions")
@Tag(name = "Permission", description = "API for permission management")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    @Operation(summary = "Get all permissions", description = "Retrieve a list of all permissions")
    public List<PermissionDTO> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @PostMapping
    @Operation(summary = "Create a new permission", description = "Create a new permission with the provided information")
    public PermissionDTO createPermission(@RequestBody SimplePermissionDTO permissionDTO) {
        return permissionService.createPermission(permissionDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a permission by ID", description = "Retrieve a permission by their ID")
    public PermissionDTO getPermissionById(@PathVariable Long id) {
        return permissionService.getPermissionById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a permission", description = "Update an existing permission's information by their ID")
    public PermissionDTO updatePermission(@PathVariable Long id, @RequestBody PermissionDTO permissionDTO) {
        return permissionService.updatePermission(id, permissionDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a permission", description = "Delete a permission by their ID")
    public void deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
    }
}