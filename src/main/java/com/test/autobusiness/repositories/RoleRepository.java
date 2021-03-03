package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);

    void saveAndFlush(Role role);
}
