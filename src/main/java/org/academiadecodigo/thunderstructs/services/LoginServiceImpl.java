package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.UserMock;
import org.academiadecodigo.thunderstructs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private UserMock app;
    private User loggedUser;
    private boolean confirmation;

    @Autowired
    public void setApp(UserMock app) {
        this.app = app;
    }

    @Override
    public User verification(String username, String password) {
        for (User user : app.getUsersMap().values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                confirmation = true;
                loggedUser = user;
            } else {
                loggedUser = null;
            }
        }
        return loggedUser;
    }

    public boolean isConfirmed() {
        return confirmation;
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}
