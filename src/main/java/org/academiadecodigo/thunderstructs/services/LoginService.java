package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.models.User;

public interface LoginService {

    void verification(String username, String password);

    boolean isConfirmed();

    User getLoggedUser();
}
