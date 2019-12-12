package org.academiadecodigo.thunderstructs.utility;

import org.academiadecodigo.thunderstructs.UserMock;
import org.academiadecodigo.thunderstructs.models.Club;
import org.academiadecodigo.thunderstructs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ClubDB {

    private HashMap<Integer, Club> clubs = new HashMap<>();
    private UserMock userMock;

    public ClubDB() {
        populate();
    }

    private void populate() {
        Club delmans = new Club();
        delmans.setId(1);
        delmans.setName("Delmans");
        delmans.setMusicGenre(MusicGenre.PIMBA);
        delmans.setUserList(new HashMap<String, User>());

        Club havana = new Club();
        havana.setId(2);
        havana.setName("Havana");
        havana.setMusicGenre(MusicGenre.POP);
        havana.setUserList(new HashMap<String, User>());

        Club ibiza = new Club();
        ibiza.setId(3);
        ibiza.setName("Ibiza");
        ibiza.setMusicGenre(MusicGenre.RAP);
        ibiza.setUserList(new HashMap<String, User>());

        Club ibiza = new Club();
        ibiza.setId(3);
        ibiza.setName("Ibiza");
        ibiza.setMusicGenre(MusicGenre.RAP);
        ibiza.setUserList(new HashMap<String, User>());

        clubs.put(delmans.getId(), delmans);
        clubs.put(havana.getId(), havana);
        clubs.put(ibiza.getId(), ibiza);

    }

    @Autowired
    public void setUserMock(UserMock userMock) {
        this.userMock = userMock;
    }

    public HashMap<Integer, Club> getClubs() {
        return clubs;
    }

}
