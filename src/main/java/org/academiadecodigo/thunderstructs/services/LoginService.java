package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.models.User;

public interface LoginService {

    User verification(String username, String password);
}
