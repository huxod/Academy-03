package com.application.controller;

import com.application.model.lesson.LessonScore;
import com.application.model.users.Role;
import com.application.model.users.Users;
import com.application.repository.LessonScoreRepository;
import com.application.repository.RolleRepository;
import com.application.repository.UsersRepository;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Stream;

@CrossOrigin(origins = {"http://localhost:8080","http://localhost:8081"})
@RestController
public class RegistrationController {

    private final UsersRepository userRepo;
    private final RolleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final LessonScoreRepository lessonScoreRepo;

    public RegistrationController(UsersRepository userRepo,
                                  RolleRepository roleRepo,
                                  PasswordEncoder passwordEncoder,
                                  LessonScoreRepository lessonScoreRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.lessonScoreRepo = lessonScoreRepo;
    }

    //Save new User
    @PreAuthorize("permitAll()")
    @PostMapping("/users-signup")
    public Users newUser(@RequestBody Users newUsers){
        newUsers.setPassword(passwordEncoder.encode(newUsers.getPassword()));
        if(roleRepo.findByRole("USER") == null){
            Stream.of("ADMIN","TEACHER","USER").forEach(name -> roleRepo.save(new Role(name)));
            Set<Role> roles = roleRepo.getByRole("USER");
            Stream.of("ADMIN","TEACHER").forEach(name -> roles.add(roleRepo.findByRole(name)));
            newUsers.setRoles(roles);
        }else{
            newUsers.setRoles(roleRepo.getByRole("USER"));
        }
        LessonScore les =new LessonScore();
        lessonScoreRepo.save(les);
        userRepo.save(newUsers);
        newUsers.setLessonScore(les);
        return userRepo.save(newUsers);
    }
    //Change User details
    @PutMapping(value = "/users/{id}" )
    public Users getPerson(@RequestBody Users user, @PathVariable Long id)  {
        Users usr = userRepo.findUsersById(id);
        user.setId(id);
        if(user.getPassword() == null){
            user.setPassword(usr.getPassword());
        }else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(roleRepo.getByRole("USER"));
        return this.userRepo.save(user);
    }
}
