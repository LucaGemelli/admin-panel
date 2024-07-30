package com.admin_panel.controller;

import com.admin_panel.dto.SimplePermissionDTO;
import com.admin_panel.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PermissionDTO>> getAllPermissions() {
        List<PermissionDTO> permissions = permissionService.getAllPermissions();
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new permission", description = "Create a new permission with the provided information")
    public ResponseEntity<PermissionDTO> createPermission(@RequestBody SimplePermissionDTO permissionDTO) {
        PermissionDTO createdPermission = permissionService.createPermission(permissionDTO);
        return new ResponseEntity<>(createdPermission, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a permission by ID", description = "Retrieve a permission by their ID")
    public ResponseEntity<PermissionDTO> getPermissionById(@PathVariable Long id) {
        PermissionDTO permission = permissionService.getPermissionById(id);
        if (permission != null) {
            return new ResponseEntity<>(permission, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a permission", description = "Update an existing permission's information by their ID")
    public ResponseEntity<PermissionDTO> updatePermission(@PathVariable Long id, @RequestBody PermissionDTO permissionDTO) {
        PermissionDTO updatedPermission = permissionService.updatePermission(id, permissionDTO);
        if (updatedPermission != null) {
            return new ResponseEntity<>(updatedPermission, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a permission", description = "Delete a permission by their ID")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        try {
            permissionService.deletePermission(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
