package org.academiadecodigo.thunderstructs.dto;

import org.academiadecodigo.thunderstructs.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDto {

    public UserDto convert(User user) {

        UserDto userDto = new UserDto();

        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setClub(user.getClub());

        return userDto;

    }
}
