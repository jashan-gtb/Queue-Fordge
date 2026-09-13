package com.jashan.queue_forge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jashan.queue_forge.dto.LoginRequestDto;
import com.jashan.queue_forge.dto.LoginResponseDto;
import com.jashan.queue_forge.dto.SignupResponseDto;
import com.jashan.queue_forge.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/auth")
public class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody LoginRequestDto  signupRequestDto) {
       return ResponseEntity.ok(authService.signup(signupRequestDto));
        
      
    }
    

    
    

}
