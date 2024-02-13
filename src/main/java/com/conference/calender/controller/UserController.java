package com.conference.calender.controller;

import com.conference.calender.model.User;
import com.conference.calender.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping(value = "/hello")
    public List<User> helloUser(){
        List<User> userList = new ArrayList<>();
        User user1 = new User("sena","imren","developer","Turkey");
        User user2 = new User("mustafa","imren","developer","Turkey");
        User user3 = new User("denova","imren","developer","Turkey");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        return userList;

    }


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
