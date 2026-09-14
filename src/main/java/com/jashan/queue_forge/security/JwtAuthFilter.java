package com.jashan.queue_forge.security;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jashan.queue_forge.Repository.UserRepository;
import com.jashan.queue_forge.models.Users;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;


// after authentication, Filters job is to fill SecurityContextHolder

@Component 
@Slf4j //simple login framework for java
public class JwtAuthFilter extends OncePerRequestFilter {  // filter comes before servlet and Interceptors comes before controller

    private final AuthUtill authUtill;
    private final UserRepository userRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;

    JwtAuthFilter(AuthUtill authUtill, UserRepository userRepository,HandlerExceptionResolver handlerExceptionResolver){
        this.handlerExceptionResolver=handlerExceptionResolver;
        this.authUtill=authUtill;
        this.userRepository=userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            try{
                log.info("incoming request: {}",request.getRequestURI());

                final String requestTokenHeader = request.getHeader("Authorization");

                if (requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer")) {
                    filterChain.doFilter(request, response); // tells filterchain to move ahead
                    return ;
                }

                String token = requestTokenHeader.substring(7); // to get pass the bearer and white space

                String userName=authUtill.getUserNameFromToken(token);

                if (userName!=null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    Users user= userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= 
                    new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    
                }
                filterChain.doFilter(request, response);}
            //GlobalExceptionHandler does not work in filter layer
            //handlerExceptionResolver will pass errors from here to GlobalExceptionHandler
            catch (Exception e){
                handlerExceptionResolver.resolveException(request,response,null,e);
            }

       
    } 
    
}
