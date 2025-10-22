package com.link_shortener.link_shortener.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByEmail(String email);
}
