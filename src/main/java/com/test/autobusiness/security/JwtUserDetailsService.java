package com.test.autobusiness.security;

import com.test.autobusiness.entities.User;
import com.test.autobusiness.entities.mappers.JwtUserMapper;
import com.test.autobusiness.security.jwt.JwtUser;
import com.test.autobusiness.security.jwt.JwtUserFactory;
import com.test.autobusiness.services.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserServiceImpl userService;

    private final JwtUserMapper userMapper;

    public JwtUserDetailsService(UserServiceImpl userService,
                                 JwtUserMapper userMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + "not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
