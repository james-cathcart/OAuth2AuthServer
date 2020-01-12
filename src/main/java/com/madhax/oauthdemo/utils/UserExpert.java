package com.madhax.oauthdemo.utils;

import com.madhax.oauthdemo.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserExpert {

    public static Collection<? extends GrantedAuthority> getAuthoritiesFromUser(User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();



        return authorities;
    }

}
