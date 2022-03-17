package com.example.usermanagement.repositories;

import com.example.usermanagement.models.Role;
import com.example.usermanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // find by name.
    Role findByName(String name);

    @Query("select r from Role r where r.name = :name")
    User findByNameQuery(@PathParam("name") String name);

    // check exists name in our role table
    boolean existsByName(String name);

    @Query("select count(r) > 0 from Role r where r.name = :name")
    boolean existsByNameQuery(@PathParam("name") String name);

}
