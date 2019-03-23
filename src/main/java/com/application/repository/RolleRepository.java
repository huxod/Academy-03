package com.application.repository;


import com.application.model.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RolleRepository extends JpaRepository<Role,Integer> {
    Set<Role> getByRole(String role);
    Role findByRole(String role);
}
