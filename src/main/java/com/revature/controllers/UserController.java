package com.revature.controllers;

import com.revature.models.User;
import com.revature.security.JwtGenerator;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final JwtGenerator jwtGenerator;

    @Autowired
    public UserController(UserService userService, JwtGenerator jwtGenerator) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping
    public List<User> getAllUsersHandler(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserByIdHandler(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUserHandler(@RequestBody User user, @PathVariable("id") int id){
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUserHandler(@PathVariable("id")int id){
        return userService.deleteUserById(id);
    }

    @PostMapping
    public User addUserHandler(@RequestBody User user){
        return userService.addUser(user);
    }



}
