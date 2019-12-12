package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.models.Club;
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
        return null;
    }

    @Override
    public void addUserToClub(String username) {

    }
}
