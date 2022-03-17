package com.example.usermanagement.repositories;

import com.example.usermanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // find by login.
    User findByLogin(String login);

    @Query("select u from User u where u.login = :login")
    User findByLoginQuery(@PathParam("login") String login);

    // check exists login in our user table
    boolean existsByLogin(String login);

    @Query("select count(u) > 0 from User u where u.login = :login")
    boolean existsByLoginQuery(@PathParam("login") String login);


    // adding user_id and role_id to the user_role database
    // native query
    @Query(value = "INSERT INTO user_role(role_id, user_id) VALUES(:role_id, :user_id)", nativeQuery = true)
    public boolean insertIntoUserRole(@PathParam("user_id") Long user_id, @PathParam("role_id") Long role_id);
}
