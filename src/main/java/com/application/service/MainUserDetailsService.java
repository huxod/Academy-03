package com.application.service;

import com.application.securityConfig.CustomUserDetails;
import com.application.model.users.Users;
import com.application.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;
    public MainUserDetailsService(UsersRepository usersRepository) {this.usersRepository = usersRepository;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = usersRepository.findByLoginOrEmail(username,username);
        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Email or Login not found"));
        return optionalUsers
                .map(CustomUserDetails::new).get();
    }
}




