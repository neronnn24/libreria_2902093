package com.app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy()
                .authorizeHttpRequests(http->{
                    http.requestMatchers(HttpMethod.GET, "auth/Hello");
                })
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager ( AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(null);
        provider.setUserDetailsService(null);
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService (){
        List<UserDetails> userDetailslist = new ArrayList<>();

        userDetailslist.add( User.withUsername("Joan")
                .password("1234")
                .roles("ADMIN")
                .authorities("READ", "CREATE")
                .build());

        userDetailslist.add( User.withUsername("Andres")
                .password("1234")
                .roles("USER")
                .authorities("READ")
                .build());



        return new InMemoryUserDetailsManager(userDetailslist);
    }
    @Bean
    public PasswordEncoder passwordEncoder (){
        return NoOpPasswordEncoder.getInstance();
    }

}
