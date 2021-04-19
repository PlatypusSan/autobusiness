package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.User;
import com.test.autobusiness.security.jwt.JwtUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface JwtUserMapper {

    JwtUser userToJwtUser(User user);

    User jwtUserToUser(JwtUser jwtUser);

    @AfterMapping
    default void mapToGrantedAuthorities(@MappingTarget JwtUser jwtUser, User user) {
        jwtUser.setAuthorities(user.getRoles()
                .stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toSet()));
    }
}
