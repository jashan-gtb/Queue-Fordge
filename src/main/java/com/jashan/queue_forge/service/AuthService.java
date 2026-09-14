package com.jashan.queue_forge.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jashan.queue_forge.Repository.UserRepository;
import com.jashan.queue_forge.dto.LoginRequestDto;
import com.jashan.queue_forge.dto.LoginResponseDto;
import com.jashan.queue_forge.dto.SignupResponseDto;
import com.jashan.queue_forge.models.Users;
import com.jashan.queue_forge.security.AuthUtill;



@Service 
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtill authUtill;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    AuthService(AuthenticationManager authenticationManager,
                AuthUtill authUtill,
                UserRepository userRepository,
                PasswordEncoder passwordEncoder
    ){
        this.authUtill=authUtill;
        this.authenticationManager=authenticationManager;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto){

        Authentication authentication= authenticationManager.authenticate( // authenticate automatically calls customisedUserDetailService
                new UsernamePasswordAuthenticationToken(loginRequestDto.userName(),loginRequestDto.userPass()) );

        Users user= (Users)authentication.getPrincipal();

        String token=authUtill.generateAcccessToken(user);

        return new LoginResponseDto(token, user.getUserId());
    }

    public SignupResponseDto signup(LoginRequestDto signupRequestDto){

        Users existingUser = userRepository.findByUserName(signupRequestDto.userName()).orElse(null);

        if (existingUser!=null) throw new IllegalArgumentException("User already exists");

         Users savedUser=userRepository.save(Users.builder()
                                    .userName(signupRequestDto.userName())
                                    .password(passwordEncoder.encode(signupRequestDto.userPass()))
                                    .build());

        return new SignupResponseDto(savedUser.getUserId(),savedUser.getUsername());
    }

}
