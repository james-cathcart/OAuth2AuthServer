package com.madhax.oauthdemo.config;

import com.madhax.oauthdemo.entity.User;
import com.madhax.oauthdemo.pojo.CustomUserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        CustomUserPrincipal customUserPrincipal = new CustomUserPrincipal(user);
        return customUserPrincipal;
    }
}
