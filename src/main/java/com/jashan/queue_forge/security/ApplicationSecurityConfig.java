package com.jashan.queue_forge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class ApplicationSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
        .csrf(csrfConfig -> csrfConfig.disable())
        .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // to avoid storing session id
        
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/technician/**").permitAll()
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers("/error").permitAll()
            .requestMatchers("/customer/**").hasAnyRole("technician","admin")
            //.requestMatchers("/customer/**").authenticated()  //This defines that requests are authenticated
        );
         //   .formLogin(Customizer.withDefaults())  
         //form login provided default form, but now we want our custom form


        return httpSecurity.build();
    } 

    @Bean // for encoding password
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean // for starting authentication manager
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
    throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
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
