package ru.volsu.alerting.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volsu.alerting.user.dao.UserGroupRepository;
import ru.volsu.alerting.user.dao.UserRepository;
import ru.volsu.alerting.user.model.User;
import ru.volsu.alerting.user.model.UserGroup;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    public void create(User user) {
        userRepository.save(user);
    }

    public void createUserGroup(UserGroup userGroup) {
        userGroupRepository.save(userGroup);
    }

    public List<User> getUsersByUserGroupName(String userGroupName) {
        return userRepository.findByUserGroupName(userGroupName);
    }

    public UserGroup getUserGroupByName(String userGroupName) {
        return userGroupRepository.findByName(userGroupName);
    }
}
