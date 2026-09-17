package com.jashan.queue_forge.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
public class ApplicationSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2successHandler oAuth2successHandler;
    public static final Logger log= LoggerFactory.getILoggerFactory().getLogger(ApplicationSecurityConfig.class.getName());

    ApplicationSecurityConfig(JwtAuthFilter jwtAuthFilter,OAuth2successHandler oAuth2successHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.oAuth2successHandler=oAuth2successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // to avoid storing session id
        
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/technician/**").permitAll()
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers("/error").permitAll()
           // .requestMatchers("/customer/**").hasAnyRole("technician","admin")
            .requestMatchers("/customer/**").authenticated()  //This defines that requests are authenticated
            .anyRequest().authenticated()
        )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)  // adds our filter
                .oauth2Login(oAuth -> oAuth
                        .failureHandler(
                        (HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) -> {
                            log.error("oAuth login error: {}", exception.getMessage());
                        }
                )
                        .successHandler(oAuth2successHandler));
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
