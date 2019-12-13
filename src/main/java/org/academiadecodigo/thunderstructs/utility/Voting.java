package org.academiadecodigo.thunderstructs.utility;


import org.academiadecodigo.thunderstructs.services.MusicGenreService;
import org.academiadecodigo.thunderstructs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Voting {


    private MusicGenreService musicGenreService;
    private UserService userService;

    public Voting() {
        populate();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMusicGenreService(MusicGenreService musicGenreService) {
        this.musicGenreService = musicGenreService;
    }

    public void populate() {
        musicGenreService.addVote(userService.getUserById("karolis"), MusicGenre.POP);
        musicGenreService.addVote(userService.getUserById("rafa"), MusicGenre.POP);
    }
}
