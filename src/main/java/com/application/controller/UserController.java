package com.application.controller;

import com.application.model.users.Role;
import com.application.model.users.Users;
import com.application.repository.UsersRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@RestController
public class UserController {
    private final UsersRepository usersRepo;

    public UserController(UsersRepository usersRepo) {
        this.usersRepo = usersRepo;
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/users/{loginOrEmail:.+}" )
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public Users checkName(@PathVariable String loginOrEmail)  {
        return usersRepo.findUsersByLoginOrEmail(loginOrEmail,loginOrEmail);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/role/{loginOrEmail:.+}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public Set<Role> getUserRole(@PathVariable String loginOrEmail){
        return usersRepo.findUsersByLoginOrEmail(loginOrEmail,loginOrEmail).getRoles();
    }
    //Delete User
    @DeleteMapping(path="/user/delete/{id}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public void deleteUser(@PathVariable Long id){ usersRepo.deleteById(id);}
}
