package com.jglitter.web;

import com.jglitter.domain.User;
import com.jglitter.domain.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Users getUsers() {
        return new Users();
    }
}
