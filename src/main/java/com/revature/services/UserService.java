package com.revature.services;

import com.revature.Exceptions.UserNotFoundException;
import com.revature.daos.RoleDAO;
import com.revature.daos.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserDAO userDAO;

    private final RoleDAO roleDAO;


    @Autowired
    public UserService(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();

    }

    public User getUserById(int id) {
        return userDAO.findById(id).orElseThrow(() -> new UserNotFoundException("No user found with id: " + id));
    }

    public User findUserByUsername(String username) {
        return userDAO.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("No user found with username: " + username));
    }

    public User updateUser(User a) {
        return userDAO.save(a);
    }

    public boolean deleteUserById(int id) {
        userDAO.deleteById(id);

        if (!userDAO.existsById(id)) {
            // Successful message
            return true;
        } else {
            return false;
        }


    }
    public User addUser(User t){
        User returnedUser = userDAO.save(t);

        if (returnedUser.getId() > 0){
            return returnedUser;
        } else{
            return null;
        }
    }

}

