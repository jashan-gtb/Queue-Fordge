package com.jashan.queue_forge.security;

import com.jashan.queue_forge.dto.LoginResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component

public class OAuth2successHandler implements AuthenticationSuccessHandler {

    private final AuthService authService;
    private final ObjectMapper objectMapper; //converts object to a json ready format

    OAuth2successHandler(AuthService authService, ObjectMapper objectMapper){
        this.authService=authService;
        this.objectMapper=objectMapper;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token= (OAuth2AuthenticationToken) authentication;
        OAuth2User user= (OAuth2User) authentication.getPrincipal();

        String registrationId=token.getAuthorizedClientRegistrationId();

        ResponseEntity<LoginResponseDto> loginResponse =authService
                .handleOauthLoginRequests(user, registrationId);

        response.setStatus(loginResponse.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper
                .writeValueAsString(loginResponse.getBody()));
    }
}
