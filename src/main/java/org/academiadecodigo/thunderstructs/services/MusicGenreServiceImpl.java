package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.models.User;
import org.academiadecodigo.thunderstructs.utility.MusicGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class MusicGenreServiceImpl implements MusicGenreService {

    private ClubService clubService;
    private Map<User, MusicGenre> voting;
    private Map<MusicGenre, Integer> counters;
    private MusicGenre popularGenre;
    private Integer value;

    public MusicGenreServiceImpl() {
        voting = new HashMap<>();
        counters = new HashMap<>();
        counters.put(MusicGenre.POP, 0);
        counters.put(MusicGenre.PIMBA, 0);
        counters.put(MusicGenre.RAP, 0);
        counters.put(MusicGenre.JAZZ, 0);
        counters.put(MusicGenre.ROCK, 0);
    }

    @Autowired
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    @Override
    public MusicGenre clubMusicGenre(int id) {
        return clubService.getClub(id).getMusicGenre();
    }

    @Override
    public MusicGenre getPopularMusic() {


        for (MusicGenre musicGenre : voting.values()) {
            int i = counters.get(musicGenre);
            counters.put(musicGenre, ++i);
        }

        for (MusicGenre musicGenre : counters.keySet()) {

            value = counters.get(musicGenre);

            if (value < counters.get(musicGenre)) {
                value = counters.get(musicGenre);
                popularGenre = musicGenre;
            }

        }

        return popularGenre;
    }

    @Override
    public MusicGenre changeGenre(int id) {
        return popularGenre;
    }

    @Override
    public void addVote(User user, MusicGenre musicGenre) {
        voting.put(user, musicGenre);

    }

    @Override
    public int winnerValue() {
        return value;
    }

    @Override
    public Map<User, MusicGenre> getVotes() {
        return voting;
    }

    @Override
    public Map<MusicGenre, Integer> getCounters() {
        return counters;
    }
}
