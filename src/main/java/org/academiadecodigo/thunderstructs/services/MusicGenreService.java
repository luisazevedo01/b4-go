package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.models.User;
import org.academiadecodigo.thunderstructs.utility.MusicGenre;

public interface MusicGenreService {

    MusicGenre clubMusicGenre(int id);

    MusicGenre getPopularMusic();

    void addVote(User user, MusicGenre musicGenre);

    MusicGenre changeGenre(int id);

    int winnerValue();

}
