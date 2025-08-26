package org.app.service;

import org.app.model.User;
import org.app.repository.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user){
        int result = userDao.register(user);
        if (result!=1){
            throw new IllegalStateException("Something went wrong");
        }
    }

    public List<User> getUsers(){
        return userDao.selectUsers();
    }


}
