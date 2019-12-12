package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.models.User;

public interface RegisterService {

    boolean registUser(User user);

    boolean registConfirmation(User user);
}
