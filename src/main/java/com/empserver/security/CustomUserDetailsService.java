package com.empserver.security;

import com.empserver.mapper.UserMapper;
import com.empserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Profile("with-security")
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userMapper.selectByEmail(email);

        return new  UserPrincipal(user.getId(), user.getEmail(), user.getPassword());
    }
}
