package com.conference.calender.controller;

import com.conference.calender.model.User;
import com.conference.calender.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;


    @PostMapping(value = "/createUser")
    public void createUser(@RequestBody User user){

        userService.createUser(user);

    }


    @GetMapping(value = "/user")
    public User getUser(@RequestBody Long userId){

       return  userService.getUser(userId);

    }


    @GetMapping(value = "/users")
    public List<User> getUserList(){

        return userService.getUserList();

    }

    @DeleteMapping(value = "/deleteUser")
    public void deleteUser(User user){

        userService.deleteUser(user);
    }





}
