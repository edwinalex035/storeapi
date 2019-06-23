package com.applaudo.store.security;

import com.applaudo.store.domain.model.User;
import com.applaudo.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("authenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username;
        String password;
        User user;
        Authentication auth;

        // Getting crediantials
        username = authentication.getName();
        password = authentication.getCredentials().toString();

        user = userService.login(username, password);
        if(user != null) {
            System.out.println(user);
            GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getCode());
            auth = new UsernamePasswordAuthenticationToken(username, password,
                    Arrays.asList(authority));
        } else {
            throw new BadCredentialsException("The credentials provide are invalid!");
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
