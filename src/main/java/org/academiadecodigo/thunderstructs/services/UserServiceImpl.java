package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.App;
import org.academiadecodigo.thunderstructs.models.Club;
import org.academiadecodigo.thunderstructs.models.User;
import org.academiadecodigo.thunderstructs.utility.ClubDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private ClubDB clubDB;
    private App app;


    @Autowired
    public void setApp(App app) {
        this.app = app;
    }
    @Autowired
    public void setClubDB(ClubDB clubDB) {
        this.clubDB = clubDB;
    }

    @Override
    public User getUserById(String username) {

        return app.getUsersMap().get(username);
    }

    @Override
    public Map<String, User> getUsersMap() {
        return app.getUsersMap();
    }

    @Override
    public Club userClub(Club option) {

        return clubDB.getClubs().get(option.getId());
    }
}
