package com.jashan.queue_forge.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;




@Configuration
public class ApplicationSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2successHandler oAuth2successHandler;
    private final HandlerExceptionResolver handlerExceptionResolver;
    public static final Logger log= LoggerFactory.getILoggerFactory().getLogger(ApplicationSecurityConfig.class.getName());

    ApplicationSecurityConfig(JwtAuthFilter jwtAuthFilter,OAuth2successHandler oAuth2successHandler,
                                HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.oAuth2successHandler=oAuth2successHandler;
        this.handlerExceptionResolver=handlerExceptionResolver;
    }

    @SuppressWarnings("null")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // to avoid storing session id
        
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers("/error").permitAll()
            .requestMatchers("/view/**").hasAnyRole("TECHNICIAN","ADMIN")
            //.requestMatchers("/customer/**").authenticated()  //This defines that requests are authenticated
        )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)  // adds our filter
                .oauth2Login(oAuth -> oAuth
                        .failureHandler(
                        (HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) -> {
                            log.error("oAuth login error: {}", exception.getMessage());
                        }
                )
                        .successHandler(oAuth2successHandler))
                        .exceptionHandling(exceptionHandelingConfigurer -> exceptionHandelingConfigurer.accessDeniedHandler(new AccessDeniedHandler() {

                            @Override
                            public void handle(HttpServletRequest request, HttpServletResponse response,
                                    org.springframework.security.access.AccessDeniedException accessDeniedException)
                                    throws IOException, ServletException {
                                
                                handlerExceptionResolver.resolveException(request,response,null,accessDeniedException);
                            }
                            
                        }));
         //   .formLogin(Customizer.withDefaults())  
         //form login provided default form, but now we want our custom form


        return httpSecurity.build();
    }



    

    /*@Bean  // this stores  users in temporary memory, but we need to store user details in database with customUserDetailService
    UserDetailsService userDetailsService(){
        UserDetails user1 = User.withUsername("jashan")
                                .password(passwordEncoder().encode("aaa"))
                                .roles("admin")
                                .build();
        

        UserDetails user2 = User.withUsername("techdaddy")
                                .password(passwordEncoder().encode("aaa"))
                                .roles("technician")
                                .build();


        return new InMemoryUserDetailsManager(user1,user2);
}*/
}
