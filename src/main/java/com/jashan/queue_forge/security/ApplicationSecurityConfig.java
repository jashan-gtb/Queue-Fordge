package com.jashan.queue_forge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class ApplicationSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(auth -> auth
            .requestMatchers("/technician/**").permitAll()
            .requestMatchers("/customer/**").hasAnyRole("technician","admin")
            //.requestMatchers("/customer/**").authenticated()  //This defines that requests are authenticated
        )
            .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    } 

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean 
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
}
}
