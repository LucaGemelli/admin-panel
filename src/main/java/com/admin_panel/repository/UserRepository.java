package com.admin_panel.repository;

import com.admin_panel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPermissions_Id(Long permissionId);
}
