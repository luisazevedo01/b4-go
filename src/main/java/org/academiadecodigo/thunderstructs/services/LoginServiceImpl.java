package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.utility.UserMock;
import org.academiadecodigo.thunderstructs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private UserMock userMock;
    private User loggedUser;
    private boolean confirmation;

    @Autowired
    public void setUserMock(UserMock userMock) {
        this.userMock = userMock;
    }

    @Override
    public void verification(String username, String password) {
        for (User user : userMock.getUsersMap().values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                confirmation = true;
                loggedUser = user;
            }
        }
    }

    @Override
    public boolean isConfirmed() {
        return confirmation;
    }

    @Override
    public User getLoggedUser() {
        return loggedUser;
    }
}
