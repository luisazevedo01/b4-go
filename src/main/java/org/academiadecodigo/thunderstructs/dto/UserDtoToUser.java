package org.academiadecodigo.thunderstructs.dto;

import org.academiadecodigo.thunderstructs.models.User;
import org.academiadecodigo.thunderstructs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUser {

    UserService userService;

    public User convert(UserDto userDto) {

        User user = new User();

        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setClub(userDto.getClub());
        user.setPassword(userService.getUserById(userDto.getUsername()).getPassword());
        user.setClubId(userService.getUserById(userDto.getUsername()).getClubId());

        return user;

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
