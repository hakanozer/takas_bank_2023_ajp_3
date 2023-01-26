package com.works.configs;

import com.works.services.AdminDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final AdminDetailService adminDetailService;
    final PasswordEncoder passwordEncoder;

    // sql -> Login
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailService).passwordEncoder(passwordEncoder);
    }

    // role -> mapping
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic()
        .and()
        .authorizeHttpRequests()
        .antMatchers("/note/**").hasRole("note")
        .antMatchers("/product/**").hasRole("product")
        .and()
        .csrf()
        .disable()
        .formLogin()
        .disable();
        http.headers().frameOptions().disable();
    }
}

/*
ali@mail.com -> note
mehmet@mail.com -> product
zehra@mail.com -> note, product
 */