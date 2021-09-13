package com.onlinebanking.dbmsonlinebanking.api;

import com.onlinebanking.dbmsonlinebanking.domain.User;
import com.onlinebanking.dbmsonlinebanking.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/user")
@RestController
public class userController {

    private final userService userService;

    @Autowired
    public userController(userService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") Long user_id) {
        return userService.getUserById(user_id)
                .orElse(null);
    }

    @PutMapping(path = "disable/{id}")
    public void disableUserById(@PathVariable("id") Long user_id) {
        userService.disableUserById(user_id);
    }

    @PutMapping(path = "update/{id}")
    public void updateUserById(@PathVariable("id") Long user_id, @RequestBody User userToUpdate) {
        userService.updateUserById(user_id, userToUpdate);
    }

}
