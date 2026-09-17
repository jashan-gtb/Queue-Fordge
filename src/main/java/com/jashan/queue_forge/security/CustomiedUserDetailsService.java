package com.jashan.queue_forge.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jashan.queue_forge.Repository.UserRepository;


@Service 

public class CustomiedUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    CustomiedUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override // for imitating InMemoryUserDetailsManager
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUserName(username).orElseThrow();
    
    }

}
