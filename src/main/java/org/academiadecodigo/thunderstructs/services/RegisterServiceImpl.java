package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.UserMock;
import org.academiadecodigo.thunderstructs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{

    private UserMock userMock;

    @Autowired
    public void setUserMock(UserMock userMock) {
        this.userMock = userMock;
    }

    @Override
    public boolean registUser(User user) {
        if(registConfirmation(user)){
            userMock.getUsersMap().put(user.getUsername(), user);
            return true;
        }
        return false;
    }

    @Override
    public boolean registConfirmation(User user) {
        for(User userX : userMock.getUsersMap().values()){
            if(user.getUsername().equals(userX.getUsername())){
                return false;
            }
        }
        return true;
    }
}
