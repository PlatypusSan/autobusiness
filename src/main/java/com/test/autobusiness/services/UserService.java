package com.test.autobusiness.services;

import com.test.autobusiness.entities.User;
import com.test.autobusiness.repositories.RoleRepository;
import com.test.autobusiness.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(user.getRoles()==null){
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        }
        userRepository.save(user);
    }
}
