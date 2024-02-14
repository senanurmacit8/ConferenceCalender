package com.conference.calender.service;

import com.conference.calender.entity.UserEntity;
import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void createUser(User user);
    public void deleteUser(User user);
    public List<User> getUserList();
    public User getUserById(Long userId) throws NotFoundException;
    public Optional<UserEntity> getUserByName(String userName) throws NotFoundException;
}
