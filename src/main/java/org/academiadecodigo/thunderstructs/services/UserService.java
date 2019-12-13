package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.models.Club;
import org.academiadecodigo.thunderstructs.models.User;
import java.util.Map;

public interface UserService {

    User getUserById(String username);

    Map<String, User> getUsersMap();

    Club userClub(Club option);

    void addUser(User user);



}
