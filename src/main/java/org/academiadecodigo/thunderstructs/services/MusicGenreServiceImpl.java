package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.utility.MusicGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicGenreServiceImpl implements MusicGenreService {

    private ClubService clubService;

    @Autowired
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    @Override
    public MusicGenre clubMusicGenre(int id) {
        return clubService.getClub(id).getMusicGenre();
    }

    @Override
    public MusicGenre changeMusicGenre() {
        return null;
    }
}
