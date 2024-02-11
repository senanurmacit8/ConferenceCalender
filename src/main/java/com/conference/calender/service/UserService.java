package com.conference.calender.service;

import com.conference.calender.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    public void createUser(User user);
    public void deleteUser(User user);
    public List<User> getUserList();
    public User getUser(Long userId);
}
