package org.academiadecodigo.thunderstructs.api;

import org.academiadecodigo.thunderstructs.models.User;
import org.academiadecodigo.thunderstructs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/user")
public class RestUserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<Map<String, User>> allUsers() {

        return new ResponseEntity<>(userService.getUsersMap(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        if (userService.getUserById(username) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.getUserById(username), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<User> registUser(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
