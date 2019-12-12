package org.academiadecodigo.thunderstructs.utility;

import org.academiadecodigo.thunderstructs.App;
import org.academiadecodigo.thunderstructs.models.Club;
import org.academiadecodigo.thunderstructs.models.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ClubDB {

    private HashMap<Integer, Club> clubs;
    private App app;

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

        Club portoPipas = new Club();
        portoPipas.setId(3);
        portoPipas.setName("Porto Pipas");
        portoPipas.setMusicGenre(MusicGenre.RAP);
        portoPipas.setUserList(new HashMap<String, User>());

        clubs.put(delmans.getId(), delmans);
        clubs.put(havana.getId(), havana);
        clubs.put(portoPipas.getId(), portoPipas);

    }

    public void setApp(App app) {
        this.app = app;
    }

    public HashMap<Integer, Club> getClubs() {
        return clubs;
    }

    public void setClubs(HashMap<Integer, Club> clubs) {
        this.clubs = clubs;
    }
}
