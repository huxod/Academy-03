package com.application.repository;

import com.application.model.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByLoginOrEmail(String login,String email);
    List<Users> findAll();
    Users findUsersByLoginOrEmail(String login,String email);
    Users findUsersById(Long id);
    void deleteById(Long id);
}
