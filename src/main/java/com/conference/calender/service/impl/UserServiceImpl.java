package com.conference.calender.service.impl;


import com.conference.calender.model.User;
import com.conference.calender.repository.user.UserRepository;
import com.conference.calender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    public void createUser(User user){

        //userRepository.save(user);

    }

    public void deleteUser(User user){

      // userRepository.delete (user);
    }

    public List<User> getUserList(){
        List<User> userList = new ArrayList<>();

      //  userList = userRepository.findAll();

        return userList;
    }

    public User getUser(Long userId){
       // Optional<User> user = userRepository.findById(userId);

        return new User();
    }

}
