package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.utility.UserMock;
import org.academiadecodigo.thunderstructs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private UserMock userMock;

    @Autowired
    public void setUserMock(UserMock userMock) {
        this.userMock = userMock;
    }

    @Override
    public boolean verification(String username, String password) {
        for (User user : userMock.getUsersMap().values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

}
