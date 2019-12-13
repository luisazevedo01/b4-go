package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.models.Club;
import org.academiadecodigo.thunderstructs.models.User;
import org.academiadecodigo.thunderstructs.utility.ClubDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubDB clubDB;

    @Autowired
    public void setClubDB(ClubDB clubDB) {
        this.clubDB = clubDB;
    }

    @Override
    public HashMap<Integer, Club> getClubs() {
        return clubDB.getClubs();
    }

    @Override
    public Club getClub(Integer id) {
        return clubDB.getClubs().get(id);
    }

    @Override
    public void addUserToClub(User user, Integer clubId) {

        if (user.getClubId() != null) {

            removeUser(user, user.getClubId());
        }

        Club club = clubDB.getClubs().get(clubId);
        club.getUserList().put(user.getUsername(), user);

    }

    @Override
    public void removeUser(User user, Integer clubId) {

        Club club = clubDB.getClubs().get(clubId);
        club.getUserList().remove(user.getUsername());

    }
}
