package org.academiadecodigo.thunderstructs.api;

import org.academiadecodigo.thunderstructs.models.Club;
import org.academiadecodigo.thunderstructs.services.ClubService;
import org.academiadecodigo.thunderstructs.utility.MusicGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/club")
public class RestClubController {

    private ClubService clubService;

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<Club>> listClubs() {

        HashMap<Integer, Club> clubsMap = clubService.getClubs();
        List<Club> clubList = new LinkedList<>();
        clubList.addAll(clubsMap.values());


        return new ResponseEntity<>(clubList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/genre/{genre}")
    public ResponseEntity<List<Club>> listClubsByGenre(@PathVariable MusicGenre genre) {

        List<Club> clubList = new LinkedList<>();
        clubList.addAll(clubService.getClubs().values());

        List<Club> clubListByGenre = new LinkedList<>();
        for (Club club : clubList) {
            if (club.getMusicGenre() == genre) {
                clubListByGenre.add(club);
            }
        }

        return new ResponseEntity<>(clubListByGenre, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Club> showClub(@PathVariable Integer id) {

        return new ResponseEntity<>(clubService.getClub(id), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/{clubId}")
    public ResponseEntity<Club> goToClub(@PathVariable Integer clubId){
        return null;
    }

    @Autowired
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }
}
