package com.conference.calender.service.impl;


import com.conference.calender.entity.UserEntity;
import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.User;
import com.conference.calender.repository.user.UserRepository;
import com.conference.calender.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;


    // update it with UserDto
    public User getUserByIdwithMapper(Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId).get();

        User user = this.modelMapper.map(userEntity, User.class);

        return user;
    }

    @Override
    public void createUser(User user){

        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setLastname(user.getLastname());
        entity.setCountry(user.getCountry());
        entity.setJob(user.getJob());

        Optional<UserEntity> userEntityByName =userRepository.findByName(user.getName());

        if(!userEntityByName.isPresent()){
            userRepository.saveAndFlush(entity);
            throw new Error("Already Exist.");
        }
    }

    @Override
    public void deleteUser(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setLastname(user.getLastname());
        userEntity.setJob(user.getJob());
        userEntity.setCountry(user.getCountry());

       userRepository.delete (userEntity);
    }

    @Override
    public List<User> getUserList(){
        List<UserEntity> userEntityList = userRepository.findAll();

        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList){
            User user = new User(userEntity.getName(),userEntity.getLastname(),
                    userEntity.getJob(),userEntity.getCountry());
            userList.add(user);
        }

        return userList;
    }

    @Override
    public User getUserById(Long userId) throws NotFoundException {
        User user = null;
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if(userEntityOptional.isPresent()){
            UserEntity  userEntity =userEntityOptional.get();

            user = new User(userEntity.getName(),
                    userEntity.getLastname(),
                    userEntity.getJob(),
                    userEntity.getCountry());
        }else {
            throw new NotFoundException("The user is not exist. UserId : " + userId);
        }
        return user;
    }

    @Override
    public Optional<UserEntity> getUserByName (String userName){
        Optional<UserEntity> userEntity = userRepository.findByName(userName);
        return userEntity;
    }

}
