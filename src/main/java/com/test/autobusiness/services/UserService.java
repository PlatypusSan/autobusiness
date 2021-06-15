package com.test.autobusiness.services;

import com.test.autobusiness.dto.AuthenticationRequest;
import com.test.autobusiness.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    ResponseEntity<Map<String, String>> login(AuthenticationRequest authenticationRequest);
}
