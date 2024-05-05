package ru.volsu.alerting.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.volsu.alerting.user.model.User;
import ru.volsu.alerting.user.model.UserGroup;
import ru.volsu.alerting.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/group")
    public void createUserGroup(@RequestBody UserGroup userGroup) {
        userService.createUserGroup(userGroup);
    }

    @GetMapping(value = "/list")
    public List<User> getUsersByUserGroupName(@RequestParam String userGroupName) {
        return userService.getUsersByUserGroupName(userGroupName);
    }
}
