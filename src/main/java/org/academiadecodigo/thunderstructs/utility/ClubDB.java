package org.academiadecodigo.thunderstructs.utility;

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

        Club havanna = new Club();
        havanna.setId(2);
        havanna.setName("Havanna");
        havanna.setMusicGenre(MusicGenre.POP);
        havanna.setUserList(new HashMap<String, User>());

        Club ibiza = new Club();
        ibiza.setId(3);
        ibiza.setName("Ibiza");
        ibiza.setMusicGenre(MusicGenre.RAP);
        ibiza.setUserList(new HashMap<String, User>());

        Club twins = new Club();
        twins.setId(4);
        twins.setName("Twins");
        twins.setMusicGenre(MusicGenre.POP);
        twins.setUserList(new HashMap<String, User>());

        clubs.put(delmans.getId(), delmans);
        clubs.put(havanna.getId(), havanna);
        clubs.put(ibiza.getId(), ibiza);
        clubs.put(twins.getId(), twins);

    }

    @Autowired
    public void setUserMock(UserMock userMock) {
        this.userMock = userMock;
    }

    public HashMap<Integer, Club> getClubs() {
        return clubs;
    }

}
