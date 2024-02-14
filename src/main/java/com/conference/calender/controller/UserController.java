package com.conference.calender.controller;

import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.User;
import com.conference.calender.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping(value = "/createUser")

    public void createUser(@RequestBody User user){

        userServiceImpl.createUser(user);
    }


    @GetMapping(value = "/user")
    public ResponseEntity<User> getUser(@RequestBody Long userId) throws NotFoundException {

        User user = this.userServiceImpl.getUserById(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    @GetMapping(value = "/users")
    public List<User> getUserList(){

        return userServiceImpl.getUserList();

    }

    @DeleteMapping(value = "/deleteUser")
    public void deleteUser(User user){

        userServiceImpl.deleteUser(user);
    }





}
