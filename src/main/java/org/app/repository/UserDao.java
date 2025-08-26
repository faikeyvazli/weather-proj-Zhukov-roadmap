package org.app.repository;

import jakarta.persistence.Entity;
import org.app.model.User;

import java.util.List;
import java.util.Optional;

/*
    We need to a register and validate the users.
    No need for all CRUD operations to be performed
    on this entity.
 */

public interface UserDao {
//    void insertUser(User user);
//    Optional<User> selectUserById(int id);
//    void update(User user);
//    void deleteUser(int id);


    int register(User user);
    List<User> selectUsers();
    Optional<User> selectUserByUsername(String username);

}
